package com.mvi.modular.integration.domain.repository

import com.mvi.modular.integration.domain.model.ApplicationInfo


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