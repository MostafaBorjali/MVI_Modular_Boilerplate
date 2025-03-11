package com.mvi.modular.integration.domain.model


internal sealed class LifecycleStatus {

    /**
     * Represent the state that application created
     */
    object Create : LifecycleStatus()

    /**
     * Represent the state that application goes foreground
     */
    object Foreground : LifecycleStatus()

    /**
     * Represent the state that application goes background
     */
    object Background : LifecycleStatus()
}
