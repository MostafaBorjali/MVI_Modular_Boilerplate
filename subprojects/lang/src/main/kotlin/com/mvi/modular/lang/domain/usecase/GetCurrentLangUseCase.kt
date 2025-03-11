package com.mvi.modular.lang.domain.usecase

import com.mvi.modular.lang.domain.manager.DeviceLanguageManager
import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.domain.repository.LanguageRepository


internal interface GetCurrentLangUseCase {
    /**
     *
     */
    operator fun invoke(): Lang
}


internal class DefaultGetCurrentLangUseCase(
    private val getAllLanguageUseCase: GetAllLanguageUseCase,
    private val languageRepository: LanguageRepository,
    private val deviceLanguageManager: DeviceLanguageManager
) : GetCurrentLangUseCase {

    override fun invoke(): Lang {
        val currentLang = languageRepository.getCurrentLanguage()
        if (currentLang != null) return currentLang

        val languages = getAllLanguageUseCase()
        val defaultLang = languages.firstOrNull { it.isDefault }
        if (defaultLang != null) return defaultLang

        val systemLanguageCode = deviceLanguageManager.getDeviceCurrentLang()
        if (systemLanguageCode != null) {
            for (lang in languages) {
                if (lang.languageCode == systemLanguageCode) {
                    return lang
                }
            }
        }

        throw IllegalStateException("language not set yet or default language not found.")
    }
}