package com.mvi.modular.notification.core

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import com.mvi.modular.notification.R


/**
 * Get current pending intent per android version
 */
internal fun notificationPendingIntentFlag(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    } else {
        PendingIntent.FLAG_UPDATE_CURRENT
    }
}


/**
 * Create notification channel
 */
internal fun Context.createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            getString(R.string.ammotel_notification_channel_id),
            getString(R.string.ammotel_notification_channel_name),
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.description = getString(R.string.ammotel_notification_channel_description)
        val notificationManager = getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }
}