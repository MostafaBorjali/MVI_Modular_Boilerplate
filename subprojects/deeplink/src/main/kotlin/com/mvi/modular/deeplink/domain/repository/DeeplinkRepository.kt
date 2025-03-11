package com.mvi.modular.deeplink.domain.repository

import com.mvi.modular.deeplink.domain.model.Deeplink


internal interface DeeplinkRepository {

    /**
     * Check there is any pending deeplink to execute
     */
    suspend fun hasPendingDeeplink(): Boolean

    /**
     * Set [Deeplink] as a pending deeplink to execute later
     */
    suspend fun setPendingDeeplink(deeplink: Deeplink)

    /**
     * Get pending [Deeplink] if exist
     *
     * @return [Deeplink] if any pending deeplink exist, false otherwise
     */
    suspend fun getPendingDeeplink(): Deeplink?
}