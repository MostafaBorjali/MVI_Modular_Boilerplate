package com.mvi.modular.notification.domain.usecase

import com.mvi.modular.notification.domain.manager.NotificationManager


internal interface RemoveFirebaseTokenUseCase {
    /**
     *
     */
    suspend operator fun invoke(): Boolean
}


internal class DefaultRemoveFirebaseTokenUseCase(
    private val notificationManager: NotificationManager,
) : RemoveFirebaseTokenUseCase {

    override suspend fun invoke(): Boolean {
        return notificationManager.removeToken()
    }
}