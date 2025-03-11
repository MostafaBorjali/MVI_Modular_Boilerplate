package com.mvi.modular.integration.domain.extension


interface LifecycleExtension {

    /**
     * Called when application create
     */
    fun onCreate() {}

    /**
     * Called when application goes to foreground
     */
    fun onForeground() {}

    /**
     * Called when application goes to background
     */
    fun onBackground() {}
}