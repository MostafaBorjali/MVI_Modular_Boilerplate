package com.ammotel.android.integration.data.repository

import com.ammotel.android.integration.domain.extension.LifecycleExtension
import com.ammotel.android.integration.domain.repository.LifecycleExtensionRepository


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