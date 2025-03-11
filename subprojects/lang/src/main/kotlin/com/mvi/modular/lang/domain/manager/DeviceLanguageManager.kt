package com.mvi.modular.lang.domain.manager

import com.mvi.modular.lang.domain.model.Lang


internal interface DeviceLanguageManager {

    /**
     * Get Device current language that user sat it before
     *
     * @return device current language, null if system not get access to language list
     */
    fun getDeviceCurrentLang(): String?

    /**
     * Get Localized string in target language from resource id and params
     *
     * @param currentLanguage Application current language
     * @param resourceId Target string resource id
     * @param args Params
     * @return localized string
     */
    fun getLocalizedStringResources(
        currentLanguage: Lang,
        resourceId: Int,
        vararg args: Any
    ): String
}