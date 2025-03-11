package com.mvi.modular.integration.data.repository

import com.mvi.modular.integration.domain.repository.LifecycleStatusRepository
import java.util.concurrent.atomic.AtomicBoolean


internal class DefaultLifecycleStatusRepository : LifecycleStatusRepository {

    /**
     *
     */
    private val isForeground = AtomicBoolean(false)


    override fun updateLifecycleStatus(foreground: Boolean) {
        isForeground.set(foreground)
    }

    override fun isApplicationForeground(): Boolean {
        return isForeground.get()
    }
}