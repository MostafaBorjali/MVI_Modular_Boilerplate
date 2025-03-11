package com.mvi.modular.deeplink.service

import com.mvi.modular.deeplink.domain.model.Deeplink


interface DeeplinkService {

    /**
     * Parse target [value] to [Deeplink] object
     *
     * @return [Deeplink] if parsed successfully, null otherwise
     */
    suspend fun parse(value: String): Deeplink?

    /**
     * Check any pending deeplink waiting to execution or not
     */
    suspend fun hasPendingExecution(): Boolean

    /**
     * Get latest pending deeplink if exist
     *
     * @return [Deeplink] if exist, null otherwise
     */
    suspend fun pendingDeeplink(): Deeplink?
}