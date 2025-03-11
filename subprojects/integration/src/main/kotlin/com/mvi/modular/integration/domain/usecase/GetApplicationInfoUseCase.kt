package com.mvi.modular.integration.domain.usecase

import com.mvi.modular.integration.domain.model.ApplicationInfo
import com.mvi.modular.integration.domain.repository.ApplicationInfoRepository


internal interface GetApplicationInfoUseCase {
    /**
     *
     */
    operator fun invoke(): ApplicationInfo?
}


internal class DefaultGetApplicationInfoUseCase(
    private val applicationInfoRepository: ApplicationInfoRepository
) : GetApplicationInfoUseCase {

    override fun invoke(): ApplicationInfo? {
        return applicationInfoRepository.getApplicationInfo()
    }
}