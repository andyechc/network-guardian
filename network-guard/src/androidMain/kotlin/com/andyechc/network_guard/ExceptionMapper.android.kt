package com.andyechc.network_guard

import java.io.IOException
import java.net.SocketTimeoutException

internal actual fun mapThrowableToNetworkError(
    throwable: Throwable,
    errorProvider: NetworkErrorProvider
): NetworkResult.Error {
    return when (throwable) {
        is SocketTimeoutException -> NetworkResult.Error.TimeoutError(
            message = errorProvider.getTimeoutErrorMessage(),
            throwable = throwable
        )
        is IOException -> NetworkResult.Error.NetworkError(
            message = errorProvider.getNetworkErrorMessage(),
            throwable = throwable
        )
        else -> NetworkResult.Error.UnknownError(
            message = errorProvider.getUnknownErrorMessage(throwable),
            throwable = throwable
        )
    }
}
