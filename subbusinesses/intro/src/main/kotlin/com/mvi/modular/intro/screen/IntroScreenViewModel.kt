package com.mvi.modular.intro.screen

import androidx.lifecycle.ViewModel
import com.mvi.modular.intro.screen.event.IntroScreenEvent
import com.mvi.modular.intro.screen.state.IntroScreenState
import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.service.LanguageService
import com.mvi.modular.persist.core.PersistConstants
import com.mvi.modular.persist.service.PersistService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class IntroScreenViewModel(
    private val languageService: LanguageService,
    private val persistService: PersistService,
) : ViewModel() {


    private val _state = MutableStateFlow(
        IntroScreenState(currentLang = languageService.getCurrentLanguage())
    )
    val uiState = _state.asStateFlow()


    fun event(event: IntroScreenEvent) {
        when (event) {
            is IntroScreenEvent.Accept -> {
                accept()
            }

            is IntroScreenEvent.GetLangConfig -> {
                languageConfig()
            }

            is IntroScreenEvent.SelectLanguage -> {
                selectLang(event.lang)
            }
        }
    }

    private fun accept() {
        persistService.putBoolean(PersistConstants.KEY_IS_INTRO_SEEN, true)
    }

    private fun languageConfig() {
        _state.update { currentState ->
            currentState.copy(
                currentLang = languageService.getCurrentLanguage(),
                languages = languageService.getAllLanguages(),
            )
        }
    }

    private fun selectLang(lang: Lang) {
        _state.update { currentState ->
            currentState.copy(
                currentLang = lang
            )
        }
        languageService.setCurrentLanguage(lang)
    }
}