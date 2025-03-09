package com.ammotel.android.integration.service

import com.ammotel.android.integration.domain.usecase.CheckApplicationIsForegroundUseCase


internal class DefaultLifecycleService(
    private val checkApplicationIsForegroundUseCase: CheckApplicationIsForegroundUseCase
) : LifecycleService {


    override fun isApplicationForeground(): Boolean {
        return checkApplicationIsForegroundUseCase()
    }
}