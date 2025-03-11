package com.mvi.modular.integration.di

import android.app.Application
import com.mvi.modular.integration.data.repository.DefaultApplicationInfoRepository
import com.mvi.modular.integration.data.repository.DefaultLifecycleExtensionRepository
import com.mvi.modular.integration.data.repository.DefaultLifecycleStatusRepository
import com.mvi.modular.integration.domain.repository.ApplicationInfoRepository
import com.mvi.modular.integration.domain.repository.LifecycleExtensionRepository
import com.mvi.modular.integration.domain.repository.LifecycleStatusRepository
import com.mvi.modular.integration.domain.usecase.DefaultAddLifecycleExtensionUseCase
import com.mvi.modular.integration.domain.usecase.DefaultCheckApplicationIsForegroundUseCase
import com.mvi.modular.integration.domain.usecase.DefaultGetApplicationInfoUseCase
import com.mvi.modular.integration.domain.usecase.DefaultNotifyApplicationLifecycleChangedUseCase
import com.mvi.modular.integration.domain.usecase.DefaultRemoveLifecycleExtensionUseCase
import com.mvi.modular.integration.domain.usecase.DefaultSetApplicationInfoUseCase
import com.mvi.modular.integration.domain.usecase.DefaultUpdateLifecycleStatusUseCase
import com.mvi.modular.integration.domain.usecase.NotifyApplicationLifecycleChangedUseCase
import com.mvi.modular.integration.lifecycle.IntegrationActivityLifecycleCallbacks
import com.mvi.modular.integration.registry.DefaultLifecycleExtensionRegistry
import com.mvi.modular.integration.registry.LifecycleExtensionRegistry
import com.mvi.modular.integration.service.ApplicationInfoService
import com.mvi.modular.integration.service.DefaultApplicationInfoService
import com.mvi.modular.integration.service.DefaultLifecycleService
import com.mvi.modular.integration.service.LifecycleService
import org.koin.dsl.module


val integrationModule = module {


    single<LifecycleExtensionRepository> { DefaultLifecycleExtensionRepository() }

    single<LifecycleStatusRepository> { DefaultLifecycleStatusRepository() }

    single<ApplicationInfoRepository> { DefaultApplicationInfoRepository() }

    factory<NotifyApplicationLifecycleChangedUseCase> {
        DefaultNotifyApplicationLifecycleChangedUseCase(get())
    }

    factory<Application.ActivityLifecycleCallbacks> {
        val updateLifecycleStatusUseCase = DefaultUpdateLifecycleStatusUseCase(get())

        IntegrationActivityLifecycleCallbacks(
            updateLifecycleStatusUseCase = updateLifecycleStatusUseCase,
            notifyApplicationLifecycleChangedUseCase = get()
        )
    }

    factory<LifecycleExtensionRegistry> {

        val addLifecycleExtensionUseCase = DefaultAddLifecycleExtensionUseCase(get())
        val removeLifecycleExtensionUseCase = DefaultRemoveLifecycleExtensionUseCase(get())

        DefaultLifecycleExtensionRegistry(
            addLifecycleExtensionUseCase = addLifecycleExtensionUseCase,
            removeLifecycleExtensionUseCase = removeLifecycleExtensionUseCase
        )
    }

    single<LifecycleService> {
        val checkApplicationIsForegroundUseCase = DefaultCheckApplicationIsForegroundUseCase(get())

        DefaultLifecycleService(checkApplicationIsForegroundUseCase)
    }

    single<ApplicationInfoService> {
        val setApplicationInfoUseCase = DefaultSetApplicationInfoUseCase(
            applicationInfoRepository = get()
        )
        val getApplicationInfoUseCase = DefaultGetApplicationInfoUseCase(
            applicationInfoRepository = get()
        )
        DefaultApplicationInfoService(
            setApplicationInfoUseCase = setApplicationInfoUseCase,
            getApplicationInfoUseCase = getApplicationInfoUseCase
        )
    }

}