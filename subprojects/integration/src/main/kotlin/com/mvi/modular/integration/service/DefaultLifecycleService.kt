package com.mvi.modular.integration.service

import com.mvi.modular.integration.domain.usecase.CheckApplicationIsForegroundUseCase


internal class DefaultLifecycleService(
    private val checkApplicationIsForegroundUseCase: CheckApplicationIsForegroundUseCase
) : LifecycleService {


    override fun isApplicationForeground(): Boolean {
        return checkApplicationIsForegroundUseCase()
    }
}