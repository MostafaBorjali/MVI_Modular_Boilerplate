package com.ammotel.android.utils.binary


/**
 * Convert string to hex string
 *
 * @receiver Target string
 * @return Encoded hex string
 */
fun String.encodeHex(): String {
    return this.toByteArray().encodeHex()
}


/**
 * Convert byte array to hex string
 *
 * @receiver Target byte array to encode
 * @return Encoded hex string
 */
fun ByteArray.encodeHex(): String {
    return joinToString("") {
        (0xFF and it.toInt()).toString(16).padStart(2, '0')
    }
}


/**
 * Convert hex string to byte array
 *
 * @receiver Encoded hex string
 * @return Decoded byte array from hex string
 */
fun String.decodeHex(): ByteArray {
    return chunked(2)
        .map { it.toInt(16).toByte() }
        .toByteArray()
}