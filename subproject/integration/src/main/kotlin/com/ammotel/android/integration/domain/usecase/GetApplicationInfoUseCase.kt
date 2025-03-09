package com.ammotel.android.integration.domain.usecase

import com.ammotel.android.integration.domain.model.ApplicationInfo
import com.ammotel.android.integration.domain.repository.ApplicationInfoRepository


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