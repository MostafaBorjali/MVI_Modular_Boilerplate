package com.mvi.modular.lang.di

import com.mvi.modular.lang.core.DefaultLocalizedContextResolver
import com.mvi.modular.lang.core.LocalizedContextResolver
import com.mvi.modular.lang.data.repository.DefaultLanguageRepository
import com.mvi.modular.lang.device.manager.DefaultDeviceLanguageManager
import com.mvi.modular.lang.domain.manager.DeviceLanguageManager
import com.mvi.modular.lang.domain.repository.LanguageRepository
import com.mvi.modular.lang.domain.usecase.CheckCurrentLangDirectionUseCase
import com.mvi.modular.lang.domain.usecase.CheckLanguageSelectedUseCase
import com.mvi.modular.lang.domain.usecase.DefaultCheckCurrentLangDirectionUseCase
import com.mvi.modular.lang.domain.usecase.DefaultCheckLanguageSelectedUseCase
import com.mvi.modular.lang.domain.usecase.DefaultGetAllLanguageUseCase
import com.mvi.modular.lang.domain.usecase.DefaultGetCurrentLangUseCase
import com.mvi.modular.lang.domain.usecase.DefaultGetLocalizedStringResourcesUseCase
import com.mvi.modular.lang.domain.usecase.DefaultSetCurrentLangUseCase
import com.mvi.modular.lang.domain.usecase.DefaultSetLangListUseCase
import com.mvi.modular.lang.domain.usecase.GetAllLanguageUseCase
import com.mvi.modular.lang.domain.usecase.GetCurrentLangUseCase
import com.mvi.modular.lang.domain.usecase.GetLocalizedStringResourcesUseCase
import com.mvi.modular.lang.domain.usecase.SetCurrentLangUseCase
import com.mvi.modular.lang.domain.usecase.SetLangListUseCase
import com.mvi.modular.lang.interceptor.LanguageInterceptor
import com.mvi.modular.lang.service.DefaultLanguageConfigService
import com.mvi.modular.lang.service.DefaultLanguageService
import com.mvi.modular.lang.service.DefaultLocalizedContextService
import com.mvi.modular.lang.service.DefaultLocalizedStringService
import com.mvi.modular.lang.service.LanguageConfigService
import com.mvi.modular.lang.service.LanguageService
import com.mvi.modular.lang.service.LocalizedContextService
import com.mvi.modular.lang.service.LocalizedStringService
import com.mvi.modular.network.core.NetworkConstants.QUALIFIER_LANGUAGE_INTERCEPTOR
import okhttp3.Interceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module


val langModule = module {

    factory<LocalizedContextResolver> {
        DefaultLocalizedContextResolver(
            context = get()
        )
    }

    single<DeviceLanguageManager> {
        DefaultDeviceLanguageManager(
            localizedContextResolver = get()
        )
    }

    single<LanguageRepository> {
        DefaultLanguageRepository(
            persistService = get()
        )
    }

    /*
     * UseCases
     */

    factory<CheckCurrentLangDirectionUseCase> {
        DefaultCheckCurrentLangDirectionUseCase(
            getCurrentLangUseCase = get()
        )
    }

    factory<CheckLanguageSelectedUseCase> {
        DefaultCheckLanguageSelectedUseCase(
            languageRepository = get()
        )
    }

    factory<GetAllLanguageUseCase> {
        DefaultGetAllLanguageUseCase(
            languageRepository = get()
        )
    }

    factory<GetCurrentLangUseCase> {
        DefaultGetCurrentLangUseCase(
            getAllLanguageUseCase = get(),
            languageRepository = get(),
            deviceLanguageManager = get()
        )
    }

    factory<GetLocalizedStringResourcesUseCase> {
        DefaultGetLocalizedStringResourcesUseCase(
            deviceLanguageManager = get(),
            getCurrentLangUseCase = get()
        )
    }

    factory<SetCurrentLangUseCase> {
        DefaultSetCurrentLangUseCase(
            getAllLanguageUseCase = get(),
            languageRepository = get()
        )
    }

    factory<SetLangListUseCase> {
        DefaultSetLangListUseCase(
            languageRepository = get()
        )
    }

    /*
     * Services
     */

    single<LanguageConfigService> {
        DefaultLanguageConfigService(
            setLangListUseCase = get()
        )
    }

    single<LanguageService> {
        DefaultLanguageService(
            setCurrentLangUseCase = get(),
            checkLanguageSelectedUseCase = get(),
            getAllLanguageUseCase = get(),
            getCurrentLangUseCase = get(),
            checkCurrentLangDirectionUseCase = get()
        )
    }

    single<LocalizedContextService> {
        DefaultLocalizedContextService(
            getCurrentLangUseCase = get(),
            localizedContextResolver = get()
        )
    }

    single<LocalizedStringService> {
        DefaultLocalizedStringService(
            getLocalizedStringResourcesUseCase = get()
        )
    }

    single<Interceptor>(named(QUALIFIER_LANGUAGE_INTERCEPTOR)) {
        LanguageInterceptor(
            languageService = get()
        )
    }
}