package com.andyechc.network_guard

sealed interface NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>
    
    sealed interface Error : NetworkResult<Nothing> {
        val message: String
        val throwable: Throwable?

        data class HttpError(
            val code: Int,
            override val message: String,
            override val throwable: Throwable? = null
        ) : Error

        data class NetworkError(
            override val message: String,
            override val throwable: Throwable? = null
        ) : Error

        data class SerializationError(
            override val message: String,
            override val throwable: Throwable? = null
        ) : Error

        data class TimeoutError(
            override val message: String,
            override val throwable: Throwable? = null
        ) : Error

        data class UnknownError(
            override val message: String,
            override val throwable: Throwable? = null
        ) : Error
    }
}
