package com.mvi.modular.lang.service

import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.domain.usecase.SetLangListUseCase


internal class DefaultLanguageConfigService(
    private val setLangListUseCase: SetLangListUseCase
) : LanguageConfigService {


    override fun setApplicationLanguages(languages: List<Lang>) {
        setLangListUseCase(languages)
    }
}