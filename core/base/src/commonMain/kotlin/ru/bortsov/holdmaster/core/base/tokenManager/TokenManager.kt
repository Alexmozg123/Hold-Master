package ru.bortsov.holdmaster.core.base.tokenManager

public interface TokenManager {
    public fun saveAccessToken(accessToken: String)
    public fun saveRefreshToken(refreshToken: String)
    public fun getAccessToken(): String
    public fun getRefreshToken(): String
    public fun clearAllTokens()
}