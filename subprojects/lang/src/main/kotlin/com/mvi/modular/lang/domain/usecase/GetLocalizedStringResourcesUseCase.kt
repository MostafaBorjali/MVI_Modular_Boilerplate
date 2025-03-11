package com.mvi.modular.lang.domain.usecase

import com.mvi.modular.lang.domain.manager.DeviceLanguageManager


internal interface GetLocalizedStringResourcesUseCase {
    /**
     *
     */
    operator fun invoke(
        resourceId: Int,
        vararg args: Any = emptyArray()
    ): String
}


internal class DefaultGetLocalizedStringResourcesUseCase(
    private val deviceLanguageManager: DeviceLanguageManager,
    private val getCurrentLangUseCase: GetCurrentLangUseCase
) : GetLocalizedStringResourcesUseCase {

    override fun invoke(resourceId: Int, vararg args: Any): String {
        val currentLang = getCurrentLangUseCase()
        return deviceLanguageManager.getLocalizedStringResources(currentLang, resourceId, *args)
    }
}