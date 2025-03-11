package com.mvi.modular.utils.date

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.milliseconds


/**
 * Convert target date from some pattern to another pattern
 * Sample: date.convert(yyyy-MM-dd'T'HH:mm:ss.SSS, HH:mm - MM/dd/yyyy)
 *
 * @receiver Target date in string format
 * @param from Source pattern
 * @param to Target pattern
 * @return Formatted date with device current timezone and apply TimeZone offset
 */
@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun String.convert(from: String, to: String): String {
    val parser = SimpleDateFormat(from, Locale.US)
    val formatter = SimpleDateFormat(to, Locale.US)
    val offset = TimeZone.getDefault().rawOffset
    val date = parser.parse(this).time + offset
    return formatter.format(Date(date))
}

/**
 * Convert target to [to] pattern
 *
 * @receiver Target date
 * @param to Target pattern
 * @return Formatted date in string
 */
fun Date.convert(to: String): String {
    val formatter = SimpleDateFormat(to, Locale.US)
    return formatter.format(this)
}

/**
 * Convert target date to [to] pattern
 *
 * @receiver Target date in second
 * @param to Target pattern
 * @return Formatted date in string
 */
fun Long.convert(to: String, timeUnit: TimeUnit = TimeUnit.SECONDS): String {
    val date = Date()
    date.time = timeUnit.toMillis(this)
    val formatter = SimpleDateFormat(to, Locale.US)
    return formatter.format(date)
}

/**
 * Add [day] count to target date
 *
 * @receiver Target date
 * @param day Day count
 * @return Updated date
 */
operator fun Date.plus(day: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.DATE, day)
    return calendar.time
}

/**
 * Calculate days left to target
 *
 * @receiver target date
 * @return days left, if days left equals or less than zero then return 1
 */
fun Long.daysLeft(): Int {
    val target = Calendar.getInstance()
    target.timeInMillis = TimeUnit.SECONDS.toMillis(this)
    val now = Calendar.getInstance()
    val diff = target.timeInMillis - now.timeInMillis
    return if (diff < 0) {
        0
    } else {
        diff.milliseconds.inWholeDays.toInt().plus(1)
    }
}