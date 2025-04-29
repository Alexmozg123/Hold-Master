package ru.bortsov.holdmaster.feature.auth.presentation.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
)
