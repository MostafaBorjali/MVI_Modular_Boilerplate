package com.mvi.modular.notification.device

import android.util.Log
import com.mvi.modular.notification.domain.manager.NotificationManager
import com.mvi.modular.strings.StringsConstants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.koin.android.ext.android.inject


class AmmotelFirebaseMessagingService : FirebaseMessagingService() {


    private val notificationManager: NotificationManager by inject()


    override fun onMessageReceived(message: RemoteMessage) {
        notificationManager.notify(message)
    }

    override fun onNewToken(token: String) {
        Log.i(StringsConstants.TAG, token)
    }
}