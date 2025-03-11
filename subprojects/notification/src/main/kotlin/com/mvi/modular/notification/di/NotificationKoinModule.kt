package com.mvi.modular.notification.di

import com.mvi.modular.notification.device.manager.DefaultNotificationManager
import com.mvi.modular.notification.domain.manager.NotificationManager
import com.mvi.modular.notification.domain.usecase.DefaultGetFirebaseTokenUseCase
import com.mvi.modular.notification.domain.usecase.DefaultRemoveFirebaseTokenUseCase
import com.mvi.modular.notification.service.DefaultNotificationService
import com.mvi.modular.notification.service.NotificationService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val notificationModule = module {

    single<NotificationManager> {
        DefaultNotificationManager(
            context = androidContext(),
            activityResolver = get(),
        )
    }

    single<NotificationService> {
        val getFirebaseTokenUseCase = DefaultGetFirebaseTokenUseCase(
            notificationManager = get(),
        )
        val removeFirebaseTokenUseCase = DefaultRemoveFirebaseTokenUseCase(
            notificationManager = get(),
        )
        DefaultNotificationService(
            getFirebaseTokenUseCase = getFirebaseTokenUseCase,
            removeFirebaseTokenUseCase = removeFirebaseTokenUseCase,
        )
    }
}