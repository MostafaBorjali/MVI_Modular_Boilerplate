package com.mvi.modular.integration.service

import com.mvi.modular.integration.domain.model.ApplicationInfo


interface ApplicationInfoService {

    /**
     * Initialize with application info from starter application class
     *
     * @param applicationInfo Current application information
     */
    fun initialize(applicationInfo: ApplicationInfo)

    /**
     * Provide current application information to all modules that have no access to this
     * configuration.
     *
     * @return Current application information
     */
    fun applicationInfo(): ApplicationInfo?
}