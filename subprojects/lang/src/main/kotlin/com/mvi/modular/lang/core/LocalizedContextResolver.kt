package com.mvi.modular.lang.core

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.LocaleList
import java.util.Locale


internal interface LocalizedContextResolver {

    /**
     * provide fresh localized context from application context
     *
     * @param currentLanguage Application current language
     * @return Localized context
     */
    fun resolveContext(currentLanguage: String): Context

    /**
     * provide fresh localized context from target context
     *
     * @param target Context
     * @param currentLanguage Application current language
     * @return Localized context
     */
    fun resolveContext(target: Context, currentLanguage: String): Context
}


internal class DefaultLocalizedContextResolver(
    private val context: Context
) : LocalizedContextResolver {

    override fun resolveContext(currentLanguage: String): Context {
        return localResolveContext(context, currentLanguage)
    }

    override fun resolveContext(target: Context, currentLanguage: String): Context {
        return localResolveContext(target, currentLanguage)
    }

    private fun localResolveContext(target: Context, currentLanguage: String): Context {
        val newLocale = Locale(currentLanguage)
        val configuration = Configuration()
        configuration.setLocale(newLocale)
        configuration.setLayoutDirection(newLocale)
        val localeList = LocaleList(newLocale)
        LocaleList.setDefault(localeList)
        configuration.setLocales(localeList)
        return ContextWrapper(target.createConfigurationContext(configuration))
    }
}