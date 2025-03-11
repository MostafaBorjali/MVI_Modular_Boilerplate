package com.mvi.modular.integration.domain.usecase

import com.mvi.modular.integration.domain.repository.LifecycleStatusRepository


internal interface UpdateLifecycleStatusUseCase {
    /**
     *
     */
    operator fun invoke(foreground: Boolean)
}


internal class DefaultUpdateLifecycleStatusUseCase(
    private val lifecycleStatusRepository: LifecycleStatusRepository
) : UpdateLifecycleStatusUseCase {

    override fun invoke(foreground: Boolean) {
        lifecycleStatusRepository.updateLifecycleStatus(foreground)
    }
}