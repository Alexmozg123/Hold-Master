package ru.bortsov.holdmaster.feature.auth.api.model

sealed class AuthState {
    data object NotAuthorized : AuthState()
    data object Authorized : AuthState()
}