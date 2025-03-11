package com.mvi.modular.lang.service

import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.domain.model.Ltr
import com.mvi.modular.lang.domain.model.Rtl
import com.mvi.modular.lang.domain.usecase.CheckCurrentLangDirectionUseCase
import com.mvi.modular.lang.domain.usecase.CheckLanguageSelectedUseCase
import com.mvi.modular.lang.domain.usecase.GetAllLanguageUseCase
import com.mvi.modular.lang.domain.usecase.GetCurrentLangUseCase
import com.mvi.modular.lang.domain.usecase.SetCurrentLangUseCase


internal class DefaultLanguageService(
    private val setCurrentLangUseCase: SetCurrentLangUseCase,
    private val checkLanguageSelectedUseCase: CheckLanguageSelectedUseCase,
    private val getAllLanguageUseCase: GetAllLanguageUseCase,
    private val getCurrentLangUseCase: GetCurrentLangUseCase,
    private val checkCurrentLangDirectionUseCase: CheckCurrentLangDirectionUseCase
) : LanguageService {


    override fun setCurrentLanguage(language: Lang): Boolean {
        return setCurrentLangUseCase(language)
    }

    override fun isLanguageSelected(): Boolean {
        return checkLanguageSelectedUseCase()
    }

    override fun getAllLanguages(): List<Lang> {
        return getAllLanguageUseCase()
    }

    override fun getCurrentLanguage(): Lang {
        return getCurrentLangUseCase()
    }

    override fun isCurrentLanguageLtr(): Boolean {
        return checkCurrentLangDirectionUseCase(Ltr)
    }

    override fun isCurrentLanguageRtl(): Boolean {
        return checkCurrentLangDirectionUseCase(Rtl)
    }

    override fun getCurrentLanguageTitle(): String {
        return getCurrentLangUseCase().nativeTitle
    }
}