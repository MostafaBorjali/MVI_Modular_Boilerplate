package com.mvi.modular.navigation.service

import android.os.Bundle
import androidx.navigation.NavHostController
import com.mvi.modular.navigation.domain.model.Destination
import com.mvi.modular.navigation.domain.usecase.AddNavigationControllerUseCase
import com.mvi.modular.navigation.domain.usecase.NavigateWithBlockUseCase
import com.mvi.modular.navigation.domain.usecase.NavigateWithParamsUseCase
import com.mvi.modular.navigation.domain.usecase.PopBackstackUseCase


internal class DefaultNavigationService(
    private val addNavigationControllerUseCase: AddNavigationControllerUseCase,
    private val navigateWithBlockUseCase: NavigateWithBlockUseCase,
    private val navigateWithParamsUseCase: NavigateWithParamsUseCase,
    private val popBackstackUseCase: PopBackstackUseCase
) : NavigationService {


    override fun initApplicationNavigationController(
        id: Long,
        navHostController: NavHostController
    ) {
        addNavigationControllerUseCase(id, navHostController)
    }

    override fun navigate(id: Long, block: (NavHostController) -> Unit) {
        navigateWithBlockUseCase(id, block)
    }

    override fun navigate(
        id: Long,
        destination: Destination,
        popupDestination: Destination?,
        saveState: Boolean,
        singleTop: Boolean,
        restoreState: Boolean,
        clearBackStack: Boolean,
        bundle: Bundle?,
        vararg args: String,
    ) {
        navigateWithParamsUseCase(
            id,
            destination,
            popupDestination,
            saveState,
            singleTop,
            restoreState,
            clearBackStack,
            bundle,
            *args,
        )
    }

    override fun pop(
        id: Long,
        destination: Destination,
        inclusive: Boolean
    ) {
        popBackstackUseCase(id, destination, inclusive)
    }

    override fun pop(id: Long) {
        popBackstackUseCase(id, null, false)
    }
}