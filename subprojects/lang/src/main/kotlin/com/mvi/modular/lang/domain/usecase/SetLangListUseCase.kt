package com.mvi.modular.lang.domain.usecase

import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.domain.repository.LanguageRepository


internal interface SetLangListUseCase {
    /**
     *
     */
    operator fun invoke(languages: List<Lang>)
}


internal class DefaultSetLangListUseCase(
    private val languageRepository: LanguageRepository
) : SetLangListUseCase {

    override fun invoke(languages: List<Lang>) {
        languageRepository.setLanguageList(languages)
    }
}