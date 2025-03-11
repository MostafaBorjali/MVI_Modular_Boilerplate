package com.mvi.modular.integration.domain.usecase

import com.mvi.modular.integration.domain.extension.LifecycleExtension
import com.mvi.modular.integration.domain.repository.LifecycleExtensionRepository


internal interface AddLifecycleExtensionUseCase {

    /**
     *
     */
    operator fun invoke(extension: LifecycleExtension)
}


internal class DefaultAddLifecycleExtensionUseCase(
    private val lifecycleExtensionRepository: LifecycleExtensionRepository
) : AddLifecycleExtensionUseCase {

    override fun invoke(extension: LifecycleExtension) {
        lifecycleExtensionRepository.addLifecycleExtension(extension)
    }
}