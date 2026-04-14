package com.andyechc.network_guard

internal expect fun mapThrowableToNetworkError(
    throwable: Throwable,
    errorProvider: NetworkErrorProvider
): NetworkResult.Error
