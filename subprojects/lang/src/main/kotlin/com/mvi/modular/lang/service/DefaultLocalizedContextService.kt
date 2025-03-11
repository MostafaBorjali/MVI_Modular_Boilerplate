package com.mvi.modular.lang.service

import android.content.Context
import com.mvi.modular.lang.core.LocalizedContextResolver
import com.mvi.modular.lang.domain.usecase.GetCurrentLangUseCase


internal class DefaultLocalizedContextService(
    private val getCurrentLangUseCase: GetCurrentLangUseCase,
    private val localizedContextResolver: LocalizedContextResolver
) : LocalizedContextService {


    override fun localizedContext(target: Context): Context {
        val lang = getCurrentLangUseCase()
        return localizedContextResolver.resolveContext(target, lang.languageCode)
    }
}