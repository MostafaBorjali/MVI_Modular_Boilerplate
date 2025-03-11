package com.mvi.modular.notification.domain.manager

import com.google.firebase.messaging.RemoteMessage


internal interface NotificationManager {

    /**
     * Notify new message received
     *
     *
     * @param remoteMessage firebase message
     */
    fun notify(remoteMessage: RemoteMessage)

    /**
     * Get current firebase token from firebase service
     */
    suspend fun getToken(): String?

    /**
     * Remove current firebase token
     *
     * @return true if successfully removed, false otherwise
     */
    suspend fun removeToken(): Boolean
}