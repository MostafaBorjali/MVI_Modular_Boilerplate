package com.mvi.modular.intro.screen.event

import com.mvi.modular.lang.domain.model.Lang


sealed interface IntroScreenEvent {

    /**
     *
     */
    data object Accept : IntroScreenEvent

    /**
     *
     */
    data object GetLangConfig : IntroScreenEvent

    /**
     *
     */
    data class SelectLanguage(val lang: Lang) : IntroScreenEvent
}