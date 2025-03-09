package com.ammotel.android.integration.domain.usecase

import com.ammotel.android.integration.domain.extension.LifecycleExtension
import com.ammotel.android.integration.domain.repository.LifecycleExtensionRepository


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