package com.mvi.modular.deeplink.service

import com.mvi.modular.deeplink.domain.model.Deeplink
import com.mvi.modular.deeplink.domain.usecase.CheckHasPendingDeeplinkUseCase
import com.mvi.modular.deeplink.domain.usecase.GetPendingDeeplinkUseCase
import com.mvi.modular.deeplink.domain.usecase.ParseDeeplinkUseCase


internal class DefaultDeeplinkService(
    private val parseDeeplinkUseCase: ParseDeeplinkUseCase,
    private val checkHasPendingDeeplinkUseCase: CheckHasPendingDeeplinkUseCase,
    private val getPendingDeeplinkUseCase: GetPendingDeeplinkUseCase,
) : DeeplinkService {


    override suspend fun parse(value: String): Deeplink? {
        return parseDeeplinkUseCase(value)
    }

    override suspend fun hasPendingExecution(): Boolean {
        return checkHasPendingDeeplinkUseCase()
    }

    override suspend fun pendingDeeplink(): Deeplink? {
        return getPendingDeeplinkUseCase()
    }
}