package com.mvi.modular.deeplink.data.repository

import com.mvi.modular.deeplink.domain.model.Deeplink
import com.mvi.modular.deeplink.domain.repository.DeeplinkRepository
import java.util.concurrent.atomic.AtomicReference


internal class DefaultDeeplinkRepository : DeeplinkRepository {


    private val pending = AtomicReference<Deeplink>()


    override suspend fun hasPendingDeeplink(): Boolean {
        return pending.get() != null
    }

    override suspend fun setPendingDeeplink(deeplink: Deeplink) {
        pending.set(deeplink)
    }

    override suspend fun getPendingDeeplink(): Deeplink? {
        return pending.getAndSet(null)
    }
}