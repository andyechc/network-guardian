package com.andyechc.network_guard

interface NetworkErrorProvider {
    fun getHttpErrorMessage(code: Int): String
    fun getNetworkErrorMessage(): String
    fun getSerializationErrorMessage(): String
    fun getTimeoutErrorMessage(): String
    fun getUnknownErrorMessage(throwable: Throwable): String
}

object DefaultNetworkErrorProvider : NetworkErrorProvider {
    override fun getHttpErrorMessage(code: Int): String = "HTTP Error: $code"
    override fun getNetworkErrorMessage(): String = "No internet connection"
    override fun getSerializationErrorMessage(): String = "Failed to parse server response"
    override fun getTimeoutErrorMessage(): String = "Request timed out"
    override fun getUnknownErrorMessage(throwable: Throwable): String = 
        throwable.message ?: "An unknown error occurred"
}
