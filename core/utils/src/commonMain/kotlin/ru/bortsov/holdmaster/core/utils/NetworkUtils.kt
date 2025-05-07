package ru.bortsov.holdmaster.core.utils

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.io.IOException

public suspend inline fun <reified S> handleResponse(
    crossinline request: suspend () -> HttpResponse,
) : ResultOf<S, ApiError> {
    return try {
        val response = request()
        when (response.status) {
            HttpStatusCode.OK -> {
                val responseBody = response.body<S>()
                ResultOf.Success(responseBody)
            }

            HttpStatusCode.Unauthorized -> {
                ResultOf.Failure(error = ApiError.NetworkErrors.UNAUTHORIZED)
            }

            HttpStatusCode.Forbidden -> {
                ResultOf.Failure(error = ApiError.NetworkErrors.FORBIDDEN)
            }

            in 500..599 -> {
                ResultOf.Failure(error = ApiError.NetworkErrors.SERVER_ERROR)
            }

            else -> ResultOf.Failure(ApiError.NetworkErrors.UNKNOWN_ERROR)
        }
    } catch (e: IOException) {
        ResultOf.Failure(ApiError.IOExceptions.CONNECTION_ERROR)
    }
}

public operator fun IntRange.contains(statusCode: HttpStatusCode): Boolean = statusCode.value in this