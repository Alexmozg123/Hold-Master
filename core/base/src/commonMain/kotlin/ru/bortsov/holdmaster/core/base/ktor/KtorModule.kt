package ru.bortsov.holdmaster.core.base.ktor

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

internal val ktorModule: Module = module {
    single<HttpClient> {
        HttpClient(KtorEngineFactory().getEngine()) {
            install(Logging) {
                logger = Logger.DEFAULT
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
                    loadTokens {
                        BearerTokens("", null)
                    }

                    refreshTokens {
                        BearerTokens("", null)
                    }
                }
            }

            defaultRequest {
                url { protocol = URLProtocol.HTTPS }
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }
}