package com.mvi.modular.navigation.di

import com.mvi.modular.navigation.data.repository.DefaultNavigationRepository
import com.mvi.modular.navigation.domain.usecase.DefaultAddNavigationControllerUseCase
import com.mvi.modular.navigation.domain.usecase.DefaultNavigateWithBlockUseCase
import com.mvi.modular.navigation.domain.usecase.DefaultNavigateWithParamsUseCase
import com.mvi.modular.navigation.domain.usecase.DefaultPopBackstackUseCase
import com.mvi.modular.navigation.service.DefaultNavigationService
import com.mvi.modular.navigation.service.NavigationService
import org.koin.dsl.module


val navigationModule = module {

    single<NavigationService> {

        val navigationRepository = DefaultNavigationRepository()

        val addNavigationControllerUseCase = DefaultAddNavigationControllerUseCase(
            navigationRepository = navigationRepository
        )
        val navigateWithBlockUseCase = DefaultNavigateWithBlockUseCase(
            navigationRepository = navigationRepository
        )
        val navigateWithParamsUseCase = DefaultNavigateWithParamsUseCase(
            navigationRepository = navigationRepository
        )
        val popBackstackUseCase = DefaultPopBackstackUseCase(
            navigationRepository = navigationRepository
        )

        DefaultNavigationService(
            addNavigationControllerUseCase = addNavigationControllerUseCase,
            navigateWithBlockUseCase = navigateWithBlockUseCase,
            navigateWithParamsUseCase = navigateWithParamsUseCase,
            popBackstackUseCase = popBackstackUseCase
        )
    }
}