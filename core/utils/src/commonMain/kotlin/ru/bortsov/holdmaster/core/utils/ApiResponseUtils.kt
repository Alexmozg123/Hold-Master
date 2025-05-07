package ru.bortsov.holdmaster.core.utils

public interface RootError

public sealed class ResultOf<out S, out E: RootError> {
    public data class Success<out R>(val value: R) : ResultOf<R, Nothing>()
    public data class Failure<out E: RootError>(val error: E) : ResultOf<Nothing, E>()
}

public sealed interface ApiError : RootError {
    public enum class NetworkErrors : ApiError {
        FORBIDDEN,
        UNAUTHORIZED,
        SERVER_ERROR,
        UNKNOWN_ERROR,
    }
    public enum class IOExceptions : ApiError {
        CONNECTION_ERROR,
    }
}