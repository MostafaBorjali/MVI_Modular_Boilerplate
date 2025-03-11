package com.mvi.modular.integration.domain.repository


internal interface LifecycleStatusRepository {

    /**
     * Update application lifecycle status
     */
    fun updateLifecycleStatus(foreground: Boolean)

    /**
     * Check application is foreground or not
     */
    fun isApplicationForeground(): Boolean
}