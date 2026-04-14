package com.andyechc.network_guardian

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform