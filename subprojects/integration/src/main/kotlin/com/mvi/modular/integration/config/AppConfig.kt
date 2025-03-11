package com.mvi.modular.integration.config

import com.mvi.modular.integration.domain.model.ApplicationType


interface AppConfig {

    /**
     *
     */
    fun getApplicationType(): ApplicationType
}