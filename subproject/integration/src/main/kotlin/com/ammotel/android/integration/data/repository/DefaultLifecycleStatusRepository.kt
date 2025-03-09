package com.ammotel.android.integration.data.repository

import com.ammotel.android.integration.domain.repository.LifecycleStatusRepository
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