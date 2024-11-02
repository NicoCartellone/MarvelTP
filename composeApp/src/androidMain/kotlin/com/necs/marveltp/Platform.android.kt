package com.necs.marveltp

import android.os.Build
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
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