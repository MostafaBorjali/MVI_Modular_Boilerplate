package com.mvi.modular.lang.domain.usecase

import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.domain.repository.LanguageRepository


internal interface GetAllLanguageUseCase {
    /**
     *
     */
    operator fun invoke(): List<Lang>
}


internal class DefaultGetAllLanguageUseCase(
    private val languageRepository: LanguageRepository
) : GetAllLanguageUseCase {

    override fun invoke(): List<Lang> {
        val languages = languageRepository.getAllLanguages()
        if (languages.isEmpty()) throw IllegalStateException("Language config not set yet.")
        return languages
    }
}