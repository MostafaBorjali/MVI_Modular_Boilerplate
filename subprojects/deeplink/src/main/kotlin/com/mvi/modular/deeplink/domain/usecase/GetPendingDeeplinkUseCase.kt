package com.mvi.modular.deeplink.domain.usecase

import com.mvi.modular.deeplink.domain.model.Deeplink
import com.mvi.modular.deeplink.domain.repository.DeeplinkRepository


internal interface GetPendingDeeplinkUseCase {
    /**
     *
     */
    suspend operator fun invoke(): Deeplink?
}


internal class DefaultGetPendingDeeplinkUseCase(
    private val deeplinkRepository: DeeplinkRepository,
) : GetPendingDeeplinkUseCase {

    override suspend fun invoke(): Deeplink? {
        return deeplinkRepository.getPendingDeeplink()
    }
}