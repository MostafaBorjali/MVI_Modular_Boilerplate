package com.mvi.modular.integration.registry

import com.mvi.modular.integration.domain.extension.LifecycleExtension
import com.mvi.modular.integration.domain.usecase.AddLifecycleExtensionUseCase
import com.mvi.modular.integration.domain.usecase.RemoveLifecycleExtensionUseCase


internal class DefaultLifecycleExtensionRegistry(
    private val addLifecycleExtensionUseCase: AddLifecycleExtensionUseCase,
    private val removeLifecycleExtensionUseCase: RemoveLifecycleExtensionUseCase
) : LifecycleExtensionRegistry {


    override fun subscribe(extension: LifecycleExtension) {
        addLifecycleExtensionUseCase(extension)
    }

    override fun unsubscribe(extension: LifecycleExtension) {
        removeLifecycleExtensionUseCase(extension)
    }
}