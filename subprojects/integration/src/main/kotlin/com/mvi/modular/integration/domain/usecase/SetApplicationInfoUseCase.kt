package com.mvi.modular.integration.domain.usecase

import com.mvi.modular.integration.domain.model.ApplicationInfo
import com.mvi.modular.integration.domain.repository.ApplicationInfoRepository


internal interface SetApplicationInfoUseCase {
    /**
     *
     */
    operator fun invoke(applicationInfo: ApplicationInfo)
}


internal class DefaultSetApplicationInfoUseCase(
    private val applicationInfoRepository: ApplicationInfoRepository
) : SetApplicationInfoUseCase {

    override fun invoke(applicationInfo: ApplicationInfo) {
        applicationInfoRepository.setApplicationInfo(applicationInfo)
    }
}