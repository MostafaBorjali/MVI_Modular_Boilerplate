package com.mvi.modular.lang.domain.repository

import com.mvi.modular.lang.domain.model.Lang


internal interface LanguageRepository {

    /**
     * Set language list
     */
    fun setLanguageList(languages: List<Lang>)

    /**
     * Set current language
     */
    fun setCurrentLanguage(language: Lang): Boolean

    /**
     * Get current language
     */
    fun getCurrentLanguage(): Lang?

    /**
     * Get all application languages
     */
    fun getAllLanguages(): List<Lang>
}