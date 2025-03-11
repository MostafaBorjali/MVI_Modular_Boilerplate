package com.mvi.modular.lang.service

import com.mvi.modular.lang.domain.model.Lang


interface LanguageConfigService {

    /**
     * Set list of language that application must support
     *
     * @param languages list of target languages
     */
    fun setApplicationLanguages(languages: List<Lang>)
}