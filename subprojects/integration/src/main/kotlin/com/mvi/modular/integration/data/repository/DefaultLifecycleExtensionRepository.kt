package com.mvi.modular.integration.data.repository

import com.mvi.modular.integration.domain.extension.LifecycleExtension
import com.mvi.modular.integration.domain.repository.LifecycleExtensionRepository


internal class DefaultLifecycleExtensionRepository : LifecycleExtensionRepository {

    /**
     *
     */
    private val extensions = arrayListOf<LifecycleExtension>()


    override fun addLifecycleExtension(extension: LifecycleExtension) {
        extensions.add(extension)
    }

    override fun removeLifecycleExtension(extension: LifecycleExtension) {
        extensions.remove(extension)
    }

    override fun getAllLifecycleExtensions(): List<LifecycleExtension> {
        return ArrayList(extensions)
    }

    override fun clearAllLifecycleExtensions() {
        extensions.clear()
    }
}