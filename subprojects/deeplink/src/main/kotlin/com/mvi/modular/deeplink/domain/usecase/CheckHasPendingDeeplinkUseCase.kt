package com.mvi.modular.deeplink.domain.usecase

import com.mvi.modular.deeplink.domain.repository.DeeplinkRepository


internal interface CheckHasPendingDeeplinkUseCase {
    /**
     *
     */
    suspend operator fun invoke(): Boolean
}


internal class DefaultCheckHasPendingDeeplinkUseCase(
    private val deeplinkRepository: DeeplinkRepository,
) : CheckHasPendingDeeplinkUseCase {

    override suspend fun invoke(): Boolean {
        return deeplinkRepository.hasPendingDeeplink()
    }
}