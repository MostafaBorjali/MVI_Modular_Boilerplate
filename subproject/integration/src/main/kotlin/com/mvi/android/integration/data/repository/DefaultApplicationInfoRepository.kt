package com.ammotel.android.integration.data.repository

import com.ammotel.android.integration.domain.model.ApplicationInfo
import com.ammotel.android.integration.domain.repository.ApplicationInfoRepository
import java.util.concurrent.atomic.AtomicReference


internal class DefaultApplicationInfoRepository : ApplicationInfoRepository {

    /**
     *
     */
    private val applicationInfo: AtomicReference<ApplicationInfo> = AtomicReference()


    override fun setApplicationInfo(applicationInfo: ApplicationInfo) {
        this.applicationInfo.set(applicationInfo)
    }

    override fun getApplicationInfo(): ApplicationInfo? {
        return applicationInfo.get()
    }
}