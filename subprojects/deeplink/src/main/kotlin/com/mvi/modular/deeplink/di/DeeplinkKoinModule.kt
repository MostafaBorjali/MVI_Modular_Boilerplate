package com.mvi.modular.deeplink.di

import com.mvi.modular.deeplink.data.repository.DefaultDeeplinkRepository
import com.mvi.modular.deeplink.device.manager.DefaultDeeplinkManager
import com.mvi.modular.deeplink.domain.manager.DeeplinkManager
import com.mvi.modular.deeplink.domain.repository.DeeplinkRepository
import com.mvi.modular.deeplink.domain.usecase.DefaultCheckHasPendingDeeplinkUseCase
import com.mvi.modular.deeplink.domain.usecase.DefaultGetPendingDeeplinkUseCase
import com.mvi.modular.deeplink.domain.usecase.DefaultParseDeeplinkUseCase
import com.mvi.modular.deeplink.domain.usecase.DefaultSetPendingDeeplinkUseCase
import com.mvi.modular.deeplink.domain.usecase.SetPendingDeeplinkUseCase
import com.mvi.modular.deeplink.service.DeeplinkService
import com.mvi.modular.deeplink.service.DefaultDeeplinkService
import org.koin.dsl.module


val deeplinkModule = module {

    single<DeeplinkManager> {
        DefaultDeeplinkManager()
    }

    single<DeeplinkRepository> {
        DefaultDeeplinkRepository()
    }

    factory<SetPendingDeeplinkUseCase> {
        DefaultSetPendingDeeplinkUseCase(
            deeplinkRepository = get(),
        )
    }

    single<DeeplinkService> {
        val parseDeeplinkUseCase = DefaultParseDeeplinkUseCase(
            deeplinkManager = get(),
        )
        val checkHasPendingDeeplinkUseCase = DefaultCheckHasPendingDeeplinkUseCase(
            deeplinkRepository = get(),
        )
        val getPendingDeeplinkUseCase = DefaultGetPendingDeeplinkUseCase(
            deeplinkRepository = get(),
        )
        DefaultDeeplinkService(
            parseDeeplinkUseCase = parseDeeplinkUseCase,
            checkHasPendingDeeplinkUseCase = checkHasPendingDeeplinkUseCase,
            getPendingDeeplinkUseCase = getPendingDeeplinkUseCase,
        )
    }
}