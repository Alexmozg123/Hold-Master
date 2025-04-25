package ru.bortsov.holdmaster.feature.auth.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
internal sealed interface AuthConfig {
    @Serializable
    data object Login : AuthConfig
    @Serializable
    data object SingUp : AuthConfig
    @Serializable
    data object Forgot : AuthConfig
    @Serializable
    data object Confirm : AuthConfig
}
