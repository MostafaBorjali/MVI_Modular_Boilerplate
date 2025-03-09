package com.ammotel.android.integration.registry

import com.ammotel.android.integration.domain.extension.LifecycleExtension
import com.ammotel.android.integration.domain.usecase.AddLifecycleExtensionUseCase
import com.ammotel.android.integration.domain.usecase.RemoveLifecycleExtensionUseCase


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