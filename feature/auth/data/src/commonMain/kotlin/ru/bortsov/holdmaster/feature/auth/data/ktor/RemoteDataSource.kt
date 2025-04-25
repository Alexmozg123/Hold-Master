package ru.bortsov.holdmaster.feature.auth.data.ktor

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.path
import ru.bortsov.holdmaster.core.utils.ApiError
import ru.bortsov.holdmaster.core.utils.ResultOf
import ru.bortsov.holdmaster.core.utils.handleResponse
import ru.bortsov.holdmaster.feature.auth.api.model.SingUpForm
import ru.bortsov.holdmaster.feature.auth.data.ktor.model.ConfirmCodeRequest
import ru.bortsov.holdmaster.feature.auth.data.ktor.model.LoginRequest
import ru.bortsov.holdmaster.feature.auth.data.ktor.model.LoginResponse
import ru.bortsov.holdmaster.feature.auth.data.ktor.model.RecoveryCodeRequest
import ru.bortsov.holdmaster.feature.auth.data.ktor.model.toSingUpFormRequest

internal class RemoteDataSource(
    private val httpClient: HttpClient,
) {

    suspend fun login(
        email: String,
        password: String,
    ): ResultOf<LoginResponse, ApiError> = handleResponse {
        httpClient.post {
            url {
                path(LOGIN_END_POINT)
                setBody(LoginRequest(email = email, password = password))
            }
        }
    }

    suspend fun singUp(singUpForm: SingUpForm): ResultOf<Unit, ApiError> = handleResponse {
        httpClient.post {
            url {
                path(SING_UP_PARAMS_END_POINT)
                setBody(singUpForm.toSingUpFormRequest())
            }
        }
    }

    suspend fun confirmCode(code: String): ResultOf<Unit, ApiError> = handleResponse {
        httpClient.post {
            url {
                path(CONFIRM_END_POINT)
                setBody(ConfirmCodeRequest(code = code))
            }
        }
    }

    suspend fun sendRecoveryCode(email: String): ResultOf<Unit, ApiError> = handleResponse {
        httpClient.post {
            url {
                path(RECOVERY_END_POINT)
                setBody(RecoveryCodeRequest(email = email))
            }
        }
    }

    private companion object {
        private const val LOGIN_END_POINT = "client/auth/login"
        private const val SING_UP_PARAMS_END_POINT = "client/auth/sign-up"
        private const val CONFIRM_END_POINT = "client/auth/confirm"
        private const val RECOVERY_END_POINT = "client/auth/recovery"
    }
}