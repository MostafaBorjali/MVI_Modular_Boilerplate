package com.mvi.modular.lang.domain.usecase

import com.mvi.modular.lang.domain.repository.LanguageRepository


internal interface CheckLanguageSelectedUseCase {
    /**
     *
     */
    operator fun invoke(): Boolean
}


internal class DefaultCheckLanguageSelectedUseCase(
    private val languageRepository: LanguageRepository
) : CheckLanguageSelectedUseCase {

    override fun invoke(): Boolean {
        return languageRepository.getCurrentLanguage() != null
    }
}