package com.ammotel.android.integration.di

import android.app.Application
import com.ammotel.android.integration.application.IntegrationApplication
import com.ammotel.android.integration.config.AppConfig
import com.ammotel.android.integration.data.repository.DefaultApplicationInfoRepository
import com.ammotel.android.integration.data.repository.DefaultLifecycleExtensionRepository
import com.ammotel.android.integration.data.repository.DefaultLifecycleStatusRepository
import com.ammotel.android.integration.domain.repository.ApplicationInfoRepository
import com.ammotel.android.integration.domain.repository.LifecycleExtensionRepository
import com.ammotel.android.integration.domain.repository.LifecycleStatusRepository
import com.ammotel.android.integration.domain.usecase.DefaultAddLifecycleExtensionUseCase
import com.ammotel.android.integration.domain.usecase.DefaultCheckApplicationIsForegroundUseCase
import com.ammotel.android.integration.domain.usecase.DefaultGetApplicationInfoUseCase
import com.ammotel.android.integration.domain.usecase.DefaultNotifyApplicationLifecycleChangedUseCase
import com.ammotel.android.integration.domain.usecase.DefaultRemoveLifecycleExtensionUseCase
import com.ammotel.android.integration.domain.usecase.DefaultSetApplicationInfoUseCase
import com.ammotel.android.integration.domain.usecase.DefaultUpdateLifecycleStatusUseCase
import com.ammotel.android.integration.domain.usecase.NotifyApplicationLifecycleChangedUseCase
import com.ammotel.android.integration.lifecycle.IntegrationActivityLifecycleCallbacks
import com.ammotel.android.integration.registry.DefaultLifecycleExtensionRegistry
import com.ammotel.android.integration.registry.LifecycleExtensionRegistry
import com.ammotel.android.integration.service.ApplicationInfoService
import com.ammotel.android.integration.service.DefaultApplicationInfoService
import com.ammotel.android.integration.service.DefaultLifecycleService
import com.ammotel.android.integration.service.LifecycleService
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

    single<AppConfig> {
        val context: Application = get()
        (context as IntegrationApplication).provideAppConfig()
    }
}