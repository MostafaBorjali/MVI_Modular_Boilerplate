package com.mvi.modular.lang.service

import android.content.Context


interface LocalizedContextService {

    /**
     * Convert target context to localized context with current application language
     *
     * @param target context
     * @return localized context
     */
    fun localizedContext(target: Context): Context
}