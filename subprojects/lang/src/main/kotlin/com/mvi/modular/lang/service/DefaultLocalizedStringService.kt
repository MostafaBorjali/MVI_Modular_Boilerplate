package com.mvi.modular.lang.service

import com.mvi.modular.lang.domain.usecase.GetLocalizedStringResourcesUseCase


internal class DefaultLocalizedStringService(
    private val getLocalizedStringResourcesUseCase: GetLocalizedStringResourcesUseCase
) : LocalizedStringService {


    override fun stringResource(resourceId: Int): String {
        return getLocalizedStringResourcesUseCase(resourceId)
    }

    override fun stringResource(resourceId: Int, vararg args: Any): String {
        return getLocalizedStringResourcesUseCase(resourceId, *args)
    }
}