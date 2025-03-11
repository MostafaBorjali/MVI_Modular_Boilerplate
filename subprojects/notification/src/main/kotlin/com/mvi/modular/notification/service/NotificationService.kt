package com.mvi.modular.notification.service


interface NotificationService {


    /**
     * Get current firebase token if exist
     *
     * @return firebase token, null if firebase not initialized or has error
     */
    suspend fun token(): String?

    /**
     * Request to remove current firebase token if exist
     *
     * @return true if firebase token exist and remove successfully, false otherwise
     */
    suspend fun removeToken(): Boolean
}