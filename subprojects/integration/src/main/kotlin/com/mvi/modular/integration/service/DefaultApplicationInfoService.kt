package com.mvi.modular.integration.service

import com.mvi.modular.integration.domain.model.ApplicationInfo
import com.mvi.modular.integration.domain.usecase.GetApplicationInfoUseCase
import com.mvi.modular.integration.domain.usecase.SetApplicationInfoUseCase


internal class DefaultApplicationInfoService(
    private val setApplicationInfoUseCase: SetApplicationInfoUseCase,
    private val getApplicationInfoUseCase: GetApplicationInfoUseCase
) : ApplicationInfoService {


    override fun initialize(applicationInfo: ApplicationInfo) {
        setApplicationInfoUseCase(applicationInfo)
    }

    override fun applicationInfo(): ApplicationInfo? {
        return getApplicationInfoUseCase()
    }
}