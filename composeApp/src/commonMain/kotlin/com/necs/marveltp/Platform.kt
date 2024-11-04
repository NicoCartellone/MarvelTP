package com.necs.marveltp

import app.cash.sqldelight.db.SqlDriver

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun currentTimeMillis(): Long

expect fun md5(input: String): String

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}