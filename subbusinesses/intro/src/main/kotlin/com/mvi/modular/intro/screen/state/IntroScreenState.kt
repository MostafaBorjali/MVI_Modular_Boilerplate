package com.mvi.modular.intro.screen.state

import com.mvi.modular.lang.domain.model.Lang


data class IntroScreenState(
    /**
     *
     */
    val currentLang: Lang,
    /**
     *
     */
    val languages: List<Lang> = emptyList(),
)