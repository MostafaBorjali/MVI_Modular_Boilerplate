package com.mvi.modular.deeplink.domain.manager

import com.mvi.modular.deeplink.domain.model.Deeplink


internal interface DeeplinkManager {

    /**
     * Parse uir to deeplink
     *
     * @return [Deeplink] if parsed successfully, null otherwise
     */
    suspend fun parse(value: String): Deeplink?
}