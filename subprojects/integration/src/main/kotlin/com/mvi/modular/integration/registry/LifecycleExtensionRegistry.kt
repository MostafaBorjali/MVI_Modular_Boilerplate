package com.mvi.modular.integration.registry

import com.mvi.modular.integration.domain.extension.LifecycleExtension


interface LifecycleExtensionRegistry {

    /**
     * Add target extension to lifecycle subscribers list
     *
     * @param extension Target lifecycle extension
     */
    fun subscribe(extension: LifecycleExtension)

    /**
     * Remove target extension from lifecycle subscribers list
     *
     * @param extension Target lifecycle extension
     */
    fun unsubscribe(extension: LifecycleExtension)
}