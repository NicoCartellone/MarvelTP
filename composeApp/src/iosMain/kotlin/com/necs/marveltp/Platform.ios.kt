package com.necs.marveltp

import platform.UIKit.UIDevice
import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970
import platform.CommonCrypto.CC_MD5
import platform.CommonCrypto.CC_MD5_DIGEST_LENGTH

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()
actual fun currentTimeMillis(): Long {
    return (NSDate().timeIntervalSince1970 * 1000).toLong() // Convertir a milisegundos
}
actual fun md5(input: String): String {
    val data = input.encodeToByteArray()
    val digest = ByteArray(CC_MD5_DIGEST_LENGTH)

    CC_MD5(data.ref, data.size.convert(), digest.ref)

    return digest.joinToString("") { "%02x".format(it) }
}