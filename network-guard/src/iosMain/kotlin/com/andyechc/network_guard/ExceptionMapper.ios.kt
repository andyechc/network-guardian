package com.andyechc.network_guard

import io.ktor.client.engine.darwin.DarwinHttpRequestException
import platform.Foundation.NSError
import platform.Foundation.NSURLErrorCannotConnectToHost
import platform.Foundation.NSURLErrorCannotFindHost
import platform.Foundation.NSURLErrorDomain
import platform.Foundation.NSURLErrorNetworkConnectionLost
import platform.Foundation.NSURLErrorNotConnectedToInternet
import platform.Foundation.NSURLErrorSecureConnectionFailed
import platform.Foundation.NSURLErrorServerCertificateHasBadDate
import platform.Foundation.NSURLErrorServerCertificateHasUnknownRoot
import platform.Foundation.NSURLErrorServerCertificateNotYetValid
import platform.Foundation.NSURLErrorServerCertificateUntrusted
import platform.Foundation.NSURLErrorTimedOut

internal actual fun mapThrowableToNetworkError(
    throwable: Throwable,
    errorProvider: NetworkErrorProvider
): NetworkResult.Error {
    return if (throwable is DarwinHttpRequestException) {
        val nsError: NSError = throwable.origin
        when {
            nsError.domain == NSURLErrorDomain && (
                nsError.code == NSURLErrorNotConnectedToInternet ||
                nsError.code == NSURLErrorCannotFindHost ||
                nsError.code == NSURLErrorCannotConnectToHost ||
                nsError.code == NSURLErrorNetworkConnectionLost ||
                nsError.code == NSURLErrorSecureConnectionFailed ||
                nsError.code == NSURLErrorServerCertificateUntrusted ||
                nsError.code == NSURLErrorServerCertificateHasBadDate ||
                nsError.code == NSURLErrorServerCertificateHasUnknownRoot ||
                nsError.code == NSURLErrorServerCertificateNotYetValid
            ) -> {
                NetworkResult.Error.NetworkError(
                    message = errorProvider.getNetworkErrorMessage(),
                    throwable = throwable
                )
            }
            nsError.domain == NSURLErrorDomain && nsError.code == NSURLErrorTimedOut -> {
                NetworkResult.Error.TimeoutError(
                    message = errorProvider.getTimeoutErrorMessage(),
                    throwable = throwable
                )
            }
            else -> {
                NetworkResult.Error.UnknownError(
                    message = errorProvider.getUnknownErrorMessage(throwable),
                    throwable = throwable
                )
            }
        }
    } else {
        NetworkResult.Error.UnknownError(
            message = errorProvider.getUnknownErrorMessage(throwable),
            throwable = throwable
        )
    }
}
