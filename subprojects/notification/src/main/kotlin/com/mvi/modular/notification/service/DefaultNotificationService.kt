package com.mvi.modular.notification.service

import com.mvi.modular.notification.domain.usecase.GetFirebaseTokenUseCase
import com.mvi.modular.notification.domain.usecase.RemoveFirebaseTokenUseCase


internal class DefaultNotificationService(
    private val getFirebaseTokenUseCase: GetFirebaseTokenUseCase,
    private val removeFirebaseTokenUseCase: RemoveFirebaseTokenUseCase,
) : NotificationService {


    override suspend fun token(): String? {
        return getFirebaseTokenUseCase()
    }

    override suspend fun removeToken(): Boolean {
        return removeFirebaseTokenUseCase()
    }
}