package ru.bortsov.holdmaster.core.base.tokenManager

import com.russhwolf.settings.Settings
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.authProviders
import io.ktor.client.plugins.auth.providers.BearerAuthProvider
import ru.bortsov.holdmaster.core.base.di.Inject

internal class TokenManagerImpl(
    private val settings: Settings,
) : TokenManager {

    /**
     * Для решения циклический зависимости HttpClient -> TokenManager было решение вынести
     * HttpClient в property, а не parametr конструктора
     * */
    private val httpClient: HttpClient by Inject.instance()

    override fun saveAccessToken(accessToken: String) {
        settings.putString(ACCESS_TOKEN_KEY, accessToken)
    }

    override fun saveRefreshToken(refreshToken: String) {
        settings.putString(REFRESH_TOKEN_KEY, refreshToken)
    }

    override fun getAccessToken(): String = settings.getString(ACCESS_TOKEN_KEY, "")

    override fun getRefreshToken(): String = settings.getString(REFRESH_TOKEN_KEY, "")

    override fun clearAllTokens() {
        httpClient.invalidateBearerTokens()
        settings.remove(ACCESS_TOKEN_KEY)
        settings.remove(REFRESH_TOKEN_KEY)
    }

    private fun HttpClient.invalidateBearerTokens() = try {
        authProviders.filterIsInstance<BearerAuthProvider>().singleOrNull()?.clearToken()
    } catch (e: IllegalStateException) {
        Napier.e(tag = "TokenManagerImpl", message = "Failed to clear token", throwable = e)
    }

    private companion object {
        private const val ACCESS_TOKEN_KEY = "access_token"
        private const val REFRESH_TOKEN_KEY = "refresh_token"
    }
}