package com.mvi.modular.integration.domain.usecase

import com.mvi.modular.integration.domain.extension.LifecycleExtension
import com.mvi.modular.integration.domain.repository.LifecycleExtensionRepository


internal interface RemoveLifecycleExtensionUseCase {
    /**
     *
     */
    operator fun invoke(extension: LifecycleExtension)
}


internal class DefaultRemoveLifecycleExtensionUseCase(
    private val lifecycleExtensionRepository: LifecycleExtensionRepository
) : RemoveLifecycleExtensionUseCase {

    override fun invoke(extension: LifecycleExtension) {
        lifecycleExtensionRepository.removeLifecycleExtension(extension)
    }
}