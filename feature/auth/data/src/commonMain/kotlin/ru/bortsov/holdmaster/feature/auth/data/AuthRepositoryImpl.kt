package ru.bortsov.holdmaster.feature.auth.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.bortsov.holdmaster.core.base.authStatusManager.AuthStatusManager
import ru.bortsov.holdmaster.core.base.tokenManager.TokenManager
import ru.bortsov.holdmaster.core.utils.ApiError
import ru.bortsov.holdmaster.core.utils.ResultOf
import ru.bortsov.holdmaster.feature.auth.api.AuthError
import ru.bortsov.holdmaster.feature.auth.api.AuthRepository
import ru.bortsov.holdmaster.feature.auth.api.model.AuthState
import ru.bortsov.holdmaster.feature.auth.api.model.SingUpForm
import ru.bortsov.holdmaster.feature.auth.data.ktor.RemoteDataSource
import ru.bortsov.holdmaster.feature.auth.data.ktor.model.LoginResponse
import ru.bortsov.holdmaster.feature.auth.data.settings.LocalDataSource

internal class AuthRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val authStatusManager: AuthStatusManager,
    private val tokenManager: TokenManager,
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): ResultOf<Unit, AuthError.LoginError> {
        return when (val result = remoteDataSource.login(email, password)) {
            is ResultOf.Success<LoginResponse> -> {
                tokenManager.saveAccessToken(result.value.accessToken)
                tokenManager.saveRefreshToken(result.value.refreshToken)
                authStatusManager.updateAuthStatus(true)
                ResultOf.Success(Unit)
            }
            is ResultOf.Failure<ApiError> -> ResultOf.Failure(AuthError.LoginError.LoginError)
        }
    }

    override suspend fun logout() {
        tokenManager.clearAllTokens()
        localDataSource.clearAllData()
        authStatusManager.updateAuthStatus(true)
    }

    override suspend fun singUp(singUpForm: SingUpForm): ResultOf<Unit, AuthError.SignUpError> {
        return when (val result = remoteDataSource.singUp(singUpForm)) {
            is ResultOf.Success<Unit> -> result
            is ResultOf.Failure<ApiError> -> ResultOf.Failure(AuthError.SignUpError.SignUpError)
        }
    }

    override suspend fun forgotPassword(
        email: String
    ): ResultOf<Unit, AuthError.ForgotPasswordError> {
        return when (val result = remoteDataSource.sendRecoveryCode(email)) {
            is ResultOf.Success<Unit> -> result
            is ResultOf.Failure<ApiError> -> {
                ResultOf.Failure(AuthError.ForgotPasswordError.ForgotPasswordError)
            }
        }
    }

    override suspend fun sendConfirmCode(
        code: String
    ): ResultOf<Unit, AuthError.SendConfirmCodeError> {
        return when (val result = remoteDataSource.confirmCode(code)) {
            is ResultOf.Success<Unit> -> result
            is ResultOf.Failure<ApiError> -> {
                ResultOf.Failure(AuthError.SendConfirmCodeError.SendConfirmCodeError)
            }
        }
    }

    override val authState: Flow<AuthState>
        get() = flow {
            authStatusManager.getAuthStatusOrNull().collect { authState ->
                when (authState) {
                    true -> emit(AuthState.Authorized)
                    false -> emit(AuthState.NotAuthorized)
                    null -> authStatusManager.updateAuthStatus(false)
                }
            }
        }
}