package com.mvi.modular.lang.data.repository

import com.mvi.modular.lang.core.LangConstants.KEY_CURRENT_LANG
import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.domain.repository.LanguageRepository
import com.mvi.modular.persist.service.PersistService


internal class DefaultLanguageRepository(
    private val persistService: PersistService
) : LanguageRepository {

    /**
     *
     */
    private val languageList = arrayListOf<Lang>()


    override fun setLanguageList(languages: List<Lang>) {
        languageList.clear()
        languageList.addAll(languages)
    }

    override fun setCurrentLanguage(language: Lang): Boolean {
        return persistService.putString(KEY_CURRENT_LANG, language.languageCode)
    }

    override fun getCurrentLanguage(): Lang? {
        val languageCode = persistService.getString(KEY_CURRENT_LANG)
        return languageList.firstOrNull { it.languageCode == languageCode }
    }

    override fun getAllLanguages(): List<Lang> {
        return languageList.filter { language -> language.isEnable }
    }
}