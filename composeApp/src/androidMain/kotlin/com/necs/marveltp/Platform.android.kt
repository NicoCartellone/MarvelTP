package com.necs.marveltp

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.necs.marvelapp.MarvelDatabase
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


actual fun currentTimeMillis(): Long {
    return System.currentTimeMillis()
}

actual fun md5(input: String): String {
    return try {
        val digest = MessageDigest.getInstance("MD5")
        val messageDigest = digest.digest(input.toByteArray())
        messageDigest.joinToString("") { "%02x".format(it) }
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
        ""
    }
}

fun createDriver(context: Context): SqlDriver {
    return AndroidSqliteDriver(
        MarvelDatabase.Schema, context,
        "marvelapp.db"
    )
}
