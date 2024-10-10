package com.necs.marveltp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform