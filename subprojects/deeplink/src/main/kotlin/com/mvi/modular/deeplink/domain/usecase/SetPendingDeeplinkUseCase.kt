package com.mvi.modular.deeplink.domain.usecase

import com.mvi.modular.deeplink.domain.model.Deeplink
import com.mvi.modular.deeplink.domain.repository.DeeplinkRepository


internal interface SetPendingDeeplinkUseCase {
    /**
     *
     */
    suspend operator fun invoke(deeplink: Deeplink)
}


internal class DefaultSetPendingDeeplinkUseCase(
    private val deeplinkRepository: DeeplinkRepository,
) : SetPendingDeeplinkUseCase {

    override suspend fun invoke(deeplink: Deeplink) {
        deeplinkRepository.setPendingDeeplink(deeplink)
    }
}