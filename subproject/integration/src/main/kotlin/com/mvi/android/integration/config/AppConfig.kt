package com.ammotel.android.integration.config

import com.ammotel.android.integration.domain.model.ApplicationType


interface AppConfig {

    /**
     *
     */
    fun getApplicationType(): ApplicationType
}