package com.andyechc.network_guard

import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

suspend fun <T> safeApiCall(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    errorProvider: NetworkErrorProvider = DefaultNetworkErrorProvider,
    apiCall: suspend () -> T
): NetworkResult<T> = withContext(coroutineContext) {
    try {
        NetworkResult.Success(apiCall())
    } catch (e: ResponseException) {
        NetworkResult.Error.HttpError(
            code = e.response.status.value,
            message = errorProvider.getHttpErrorMessage(e.response.status.value),
            throwable = e
        )
    } catch (e: ConnectTimeoutException) {
        NetworkResult.Error.TimeoutError(
            message = errorProvider.getTimeoutErrorMessage(),
            throwable = e
        )
    } catch (e: SocketTimeoutException) {
        NetworkResult.Error.TimeoutError(
            message = errorProvider.getTimeoutErrorMessage(),
            throwable = e
        )
    } catch (e: Throwable) {
        mapThrowableToNetworkError(e, errorProvider)
    }
}
