package com.ammotel.android.integration.domain.repository

import com.ammotel.android.integration.domain.model.ApplicationInfo


internal interface ApplicationInfoRepository {

    /**
     * Set application information
     */
    fun setApplicationInfo(applicationInfo: ApplicationInfo)

    /**
     * Get application information
     */
    fun getApplicationInfo(): ApplicationInfo?
}