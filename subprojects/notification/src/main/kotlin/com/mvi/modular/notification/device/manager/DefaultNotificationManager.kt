package com.mvi.modular.notification.device.manager

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import com.mvi.modular.integration.resolver.ActivityResolver
import com.mvi.modular.notification.R
import com.mvi.modular.notification.core.NotificationConstants.NOTIFICATION_REQUEST_CODE
import com.mvi.modular.notification.core.createNotificationChannel
import com.mvi.modular.notification.core.notificationPendingIntentFlag
import com.mvi.modular.notification.domain.manager.NotificationManager
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


internal class DefaultNotificationManager(
    private val context: Context,
    private val activityResolver: ActivityResolver,
) : NotificationManager {


    override fun notify(remoteMessage: RemoteMessage) {
        val mainActivity = activityResolver.resolve(ActivityResolver.ID.ACTIVITY_ID_MAIN)
        val intent = Intent(context, mainActivity).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_REQUEST_CODE,
            intent,
            notificationPendingIntentFlag()
        )

        val channelId = context.getString(R.string.ammotel_notification_channel_id)

        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(remoteMessage.notification?.title)
            .setContentText(remoteMessage.notification?.body)
            .setSmallIcon(R.drawable.ic_notification)
            .setColor(
                ContextCompat.getColor(
                    context,
                    com.mvi.modular.ui.R.color.primary_first_color
                )
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setWhen(System.currentTimeMillis())
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        with(context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager) {
            context.createNotificationChannel()
            val notification = builder.build()
            notify(NOTIFICATION_REQUEST_CODE, notification)
        }
    }

    override suspend fun getToken(): String? {
        return suspendCancellableCoroutine { continuation ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (continuation.isActive && !continuation.isCancelled) {
                    if (!task.isSuccessful) {
                        continuation.resume(null)
                    }

                    continuation.resume(task.result)
                }
            }
        }
    }

    override suspend fun removeToken(): Boolean {
        return suspendCancellableCoroutine { continuation ->
            FirebaseMessaging.getInstance().deleteToken().addOnCompleteListener { task ->
                if (continuation.isActive && !continuation.isCancelled) {
                    if (task.isSuccessful) {
                        continuation.resume(true)
                    } else {
                        continuation.resume(false)
                    }
                }
            }
        }
    }
}