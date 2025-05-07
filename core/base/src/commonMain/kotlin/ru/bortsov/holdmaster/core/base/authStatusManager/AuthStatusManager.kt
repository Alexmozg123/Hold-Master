package ru.bortsov.holdmaster.core.base.authStatusManager

import kotlinx.coroutines.flow.Flow

public interface AuthStatusManager {
    public fun updateAuthStatus(authStatus: Boolean)
    public fun getAuthStatusOrNull(): Flow<Boolean?>
}