package com.mvi.modular.notification.domain.extension


interface NotificationExtension {

    /**
     * Called whenever any notification message received from firebase service
     *
     * @param data notification message data
     */
    fun onNotificationReceived(data: Map<String, String?>): Boolean

    /**
     * Called whenever firebase service change token
     *
     * @param token firebase token
     */
    fun onTokenReceived(token: String)
}