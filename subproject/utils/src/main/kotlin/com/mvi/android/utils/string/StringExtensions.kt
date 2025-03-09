package com.mvi.android.utils.string

import android.annotation.SuppressLint
import android.icu.text.DecimalFormatSymbols
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Locale.ENGLISH


/**
 * Build a string that cares about string content RTL or LTR, so change string equals to language direction.
 *
 * @param block the block of creating string with string builder receiver
 * @return the directional string
 */
inline fun buildDirectionalString(block: StringBuilder.() -> Unit): String {
    return buildString {
        append("\u202B")
        block(this)
        append("\u202C")
    }
}

/**
 * Build a string that dose not care about string content RTL or LTR, so build string from left to right.
 *
 * @param block the block of creating string with string builder receiver
 * @return the non directional string
 */
inline fun buildNonDirectionalString(block: StringBuilder.() -> Unit): String {
    return buildString {
        append("\u202A")
        block(this)
        append("\u202C")
    }
}

/**
 * This method is to change the country code to flag
 *
 * @receiver target country code
 * @return flag of target country code
 */
fun String.toFlagEmoji(): String {
    if (this.length != 2) {
        return this
    }

    val countryCodeCaps = this.uppercase()
    val firstLetter = Character.codePointAt(countryCodeCaps, 0) - 0x41 + 0x1F1E6
    val secondLetter = Character.codePointAt(countryCodeCaps, 1) - 0x41 + 0x1F1E6

    if (!countryCodeCaps[0].isLetter() || !countryCodeCaps[1].isLetter()) {
        return this
    }
    return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
}

/**
 * Convert second to timer styled string
 *
 * @param second target second
 * @return string that show input as timer sample
 */
@SuppressLint("DefaultLocale")
fun second2TimeString(second: Long): String {
    val seconds = second % 60
    var minutes = second / 60
    if (minutes >= 60) {
        val hours = minutes / 60
        minutes %= 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
    return String.format("%02d:%02d", minutes, seconds)
}

/**
 * Convert data volume (mb to gb)
 *
 * @param mb data volume
 * @return represent data on gb if reached the volume, mb otherwise
 */
fun mb2String(mb: Long, spacing: Boolean = true): String {
    val volume = mb / 1024f
    val formatter = DecimalFormat("0.#")
    formatter.roundingMode = RoundingMode.DOWN
    return if (volume >= 1) {
        if (spacing) "${formatter.format(volume)} GB" else "${formatter.format(volume)}GB"
    } else {
        if (spacing) "${formatter.format(mb)} MB" else "${formatter.format(mb)}MB"
    }
}

/**
 * Convert amount with dollar sign preview
 */
fun Long.amountPreview(): String {
    val amount = this / 100f
    val symbol = DecimalFormatSymbols(ENGLISH)
    val formatter = android.icu.text.DecimalFormat("0.##", symbol)
    return "$${formatter.format(amount)}"
}