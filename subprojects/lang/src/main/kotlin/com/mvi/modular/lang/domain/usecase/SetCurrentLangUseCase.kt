package com.mvi.modular.lang.domain.usecase

import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.domain.repository.LanguageRepository


internal interface SetCurrentLangUseCase {
    /**
     *
     */
    operator fun invoke(language: Lang): Boolean
}


internal class DefaultSetCurrentLangUseCase(
    private val getAllLanguageUseCase: GetAllLanguageUseCase,
    private val languageRepository: LanguageRepository
) : SetCurrentLangUseCase {

    override fun invoke(language: Lang): Boolean {
        if (language in getAllLanguageUseCase()) {
            return languageRepository.setCurrentLanguage(language)
        } else {
            throw IllegalStateException(
                "Trying to set a language as current language that not exist in language list."
            )
        }
    }
}