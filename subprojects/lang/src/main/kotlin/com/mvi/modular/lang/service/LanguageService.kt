package com.mvi.modular.lang.service

import com.mvi.modular.lang.domain.model.Lang


interface LanguageService {

    /**
     * Set application current language to target language
     *
     * @param language Target language
     */
    fun setCurrentLanguage(language: Lang): Boolean

    /**
     * Check is any language set as application language
     *
     * @return true if any language has already set, false otherwise.
     */
    fun isLanguageSelected(): Boolean

    /**
     * Get all languages that application supported
     *
     * @return supported language list
     */
    fun getAllLanguages(): List<Lang>

    /**
     * Get current language that application works with it
     *
     * @return current language
     */
    fun getCurrentLanguage(): Lang

    /**
     * Check is application current language direction is left to right
     *
     * @return true if current language direction is left to right, false otherwise
     */
    fun isCurrentLanguageLtr(): Boolean

    /**
     * Check is application current language direction is right to left
     *
     * @return true if current language direction is right to left, false otherwise
     */
    fun isCurrentLanguageRtl(): Boolean

    /**
     * Get current language NATIVE title
     *
     * @return current language title
     */
    fun getCurrentLanguageTitle(): String
}