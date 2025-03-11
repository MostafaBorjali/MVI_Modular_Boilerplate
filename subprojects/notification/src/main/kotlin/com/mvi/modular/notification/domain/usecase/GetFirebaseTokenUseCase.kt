package com.mvi.modular.notification.domain.usecase

import com.mvi.modular.notification.domain.manager.NotificationManager


internal interface GetFirebaseTokenUseCase {
    /**
     *
     */
    suspend operator fun invoke(): String?
}


internal class DefaultGetFirebaseTokenUseCase(
    private val notificationManager: NotificationManager,
) : GetFirebaseTokenUseCase {

    override suspend fun invoke(): String? {
        return notificationManager.getToken()
    }
}