package ru.bortsov.holdmaster.feature.auth.api

import kotlinx.coroutines.flow.Flow
import ru.bortsov.holdmaster.core.utils.ResultOf
import ru.bortsov.holdmaster.core.utils.RootError
import ru.bortsov.holdmaster.feature.auth.api.model.AuthState
import ru.bortsov.holdmaster.feature.auth.api.model.SingUpForm

interface AuthRepository {
    suspend fun login(email: String, password: String): ResultOf<Unit, AuthError.LoginError>
    suspend fun logout()
    suspend fun singUp(singUpForm: SingUpForm): ResultOf<Unit, AuthError.SignUpError>
    suspend fun forgotPassword(email: String): ResultOf<Unit, AuthError.ForgotPasswordError>
    suspend fun sendConfirmCode(code: String): ResultOf<Unit, AuthError.SendConfirmCodeError>
    val authState: Flow<AuthState>
}

sealed interface AuthError : RootError {
    enum class LoginError : AuthError {
        LoginError
    }
    enum class SignUpError : AuthError {
        SignUpError
    }
    enum class ForgotPasswordError : AuthError {
        ForgotPasswordError
    }
    enum class SendConfirmCodeError : AuthError {
        SendConfirmCodeError
    }
}