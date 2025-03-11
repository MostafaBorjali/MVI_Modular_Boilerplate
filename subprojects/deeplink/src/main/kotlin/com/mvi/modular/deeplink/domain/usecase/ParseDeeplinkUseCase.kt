package com.mvi.modular.deeplink.domain.usecase

import com.mvi.modular.deeplink.domain.manager.DeeplinkManager
import com.mvi.modular.deeplink.domain.model.Deeplink


internal interface ParseDeeplinkUseCase {
    /**
     *
     */
    suspend operator fun invoke(value: String): Deeplink?
}


internal class DefaultParseDeeplinkUseCase(
    private val deeplinkManager: DeeplinkManager,
) : ParseDeeplinkUseCase {

    override suspend fun invoke(value: String): Deeplink? {
        return deeplinkManager.parse(value)
    }
}