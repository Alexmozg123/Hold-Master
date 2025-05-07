package ru.bortsov.holdmaster.core.base.authStatusManager

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getBooleanOrNullFlow
import kotlinx.coroutines.flow.Flow

internal class AuthStatusManagerImpl(
    private val settings: ObservableSettings,
) : AuthStatusManager {

    override fun updateAuthStatus(authStatus: Boolean) {
        settings.putBoolean(AUTH_STATUS, authStatus)
    }

    @OptIn(ExperimentalSettingsApi::class)
    override fun getAuthStatusOrNull(): Flow<Boolean?> {
        return settings.getBooleanOrNullFlow(AUTH_STATUS)
    }

    private companion object{
        private const val AUTH_STATUS = "auth_status"
    }
}