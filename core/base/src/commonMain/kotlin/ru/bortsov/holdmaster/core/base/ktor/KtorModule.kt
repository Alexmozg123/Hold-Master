package ru.bortsov.holdmaster.core.base.ktor

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.RefreshTokensParams
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.parameters
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.bortsov.holdmaster.core.base.authStatusManager.AuthStatusManager
import ru.bortsov.holdmaster.core.base.di.Inject
import ru.bortsov.holdmaster.core.base.tokenManager.Token
import ru.bortsov.holdmaster.core.base.tokenManager.TokenManager
import ru.bortsov.holdmaster.core.utils.ApiError
import ru.bortsov.holdmaster.core.utils.ResultOf
import ru.bortsov.holdmaster.core.utils.handleResponse
import ru.bortsov.holdmaster.feature.auth.api.AuthRepository

internal val ktorModule: Module = module {
    single<HttpClient> {
        HttpClient(KtorEngineFactory().getEngine()) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 30000
                socketTimeoutMillis = 15000
            }

            install(Auth) {
                bearer {
                    val tokenManager: TokenManager by Inject.instance()
                    val authStatusManager: AuthStatusManager by Inject.instance()
                    val authRepository: AuthRepository by Inject.instance()

                    loadTokens {
                        loadTokens(tokenManager)
                    }

                    refreshTokens {
                        refreshTokens(tokenManager, authStatusManager, authRepository)
                    }
                }
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "holdmaster.ru"
                    path("/api/")
                }
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }
}

private fun loadTokens(tokenManager: TokenManager): BearerTokens? {
    val bearerToken = tokenManager.getAccessToken()
    val refreshToken = tokenManager.getRefreshToken()

    if (bearerToken.isBlank() || refreshToken.isBlank()) return null

    return BearerTokens(accessToken = bearerToken, refreshToken = refreshToken)
}

private suspend fun RefreshTokensParams.refreshTokens(
    tokenManager: TokenManager,
    authStatusManager: AuthStatusManager,
    authRepository: AuthRepository,
): BearerTokens? {
    val result: ResultOf<Token, ApiError> = handleResponse {
        client.submitForm(
            url = "client/refresh",
            formParameters = parameters {
                append("Token", tokenManager.getRefreshToken())
            }
        ) { markAsRefreshTokenRequest() }
    }

    return when (result) {
        is ResultOf.Success -> {
            tokenManager.saveAccessToken(result.value.accessToken)
            tokenManager.saveRefreshToken(result.value.refreshToken)
            BearerTokens(
                accessToken = result.value.accessToken,
                refreshToken = result.value.refreshToken
            )
        }

        is ResultOf.Failure -> {
            when (result.error) {
                ApiError.NetworkErrors.UNAUTHORIZED -> {
                    authStatusManager.updateAuthStatus(false)
                    authRepository.logout()
                    tokenManager.clearAllTokens()
                    null
                }

                else -> null
            }
        }
    }
}