package com.ammotel.android.integration.domain.usecase

import com.ammotel.android.integration.domain.model.ApplicationInfo
import com.ammotel.android.integration.domain.repository.ApplicationInfoRepository


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