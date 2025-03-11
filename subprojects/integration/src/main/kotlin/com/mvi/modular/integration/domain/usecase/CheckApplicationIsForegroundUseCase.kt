package com.mvi.modular.integration.domain.usecase

import com.mvi.modular.integration.domain.repository.LifecycleStatusRepository


internal interface CheckApplicationIsForegroundUseCase {
    /**
     *
     */
    operator fun invoke(): Boolean
}


internal class DefaultCheckApplicationIsForegroundUseCase(
    private val lifecycleStatusRepository: LifecycleStatusRepository
) : CheckApplicationIsForegroundUseCase {

    override fun invoke(): Boolean {
        return lifecycleStatusRepository.isApplicationForeground()
    }
}