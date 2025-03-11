package com.mvi.modular.integration.domain.usecase

import com.mvi.modular.integration.domain.model.LifecycleStatus
import com.mvi.modular.integration.domain.repository.LifecycleExtensionRepository


internal interface NotifyApplicationLifecycleChangedUseCase {
    /**
     *
     */
    operator fun invoke(status: LifecycleStatus)
}


internal class DefaultNotifyApplicationLifecycleChangedUseCase(
    private val lifecycleExtensionRepository: LifecycleExtensionRepository
) : NotifyApplicationLifecycleChangedUseCase {

    override fun invoke(status: LifecycleStatus) {
        lifecycleExtensionRepository.getAllLifecycleExtensions().forEach { extension ->
            when (status) {
                LifecycleStatus.Create -> extension.onCreate()
                LifecycleStatus.Foreground -> extension.onForeground()
                LifecycleStatus.Background -> extension.onBackground()
            }
        }
    }
}