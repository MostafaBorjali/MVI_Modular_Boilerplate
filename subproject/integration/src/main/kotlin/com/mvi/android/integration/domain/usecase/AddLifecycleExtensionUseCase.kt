package com.ammotel.android.integration.domain.usecase

import com.ammotel.android.integration.domain.extension.LifecycleExtension
import com.ammotel.android.integration.domain.repository.LifecycleExtensionRepository


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