package com.ammotel.android.integration.service

import com.ammotel.android.integration.domain.model.ApplicationInfo
import com.ammotel.android.integration.domain.usecase.GetApplicationInfoUseCase
import com.ammotel.android.integration.domain.usecase.SetApplicationInfoUseCase


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