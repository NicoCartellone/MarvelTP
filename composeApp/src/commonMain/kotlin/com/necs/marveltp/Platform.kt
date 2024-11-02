package com.necs.marveltp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
expect fun currentTimeMillis(): Long
expect fun md5(input: String): String