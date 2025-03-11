package com.mvi.modular.navigation.domain.usecase

import android.os.Bundle
import androidx.navigation.navOptions
import com.mvi.modular.navigation.domain.model.Destination
import com.mvi.modular.navigation.domain.repository.NavigationRepository


internal interface NavigateWithParamsUseCase {
    /**
     *
     */
    operator fun invoke(
        id: Long,
        destination: Destination,
        popupDestination: Destination? = null,
        saveState: Boolean = false,
        singleTop: Boolean = false,
        restoreState: Boolean = false,
        clearBackStack: Boolean = false,
        bundle: Bundle? = null,
        vararg args: String,
    )
}


internal class DefaultNavigateWithParamsUseCase(
    private val navigationRepository: NavigationRepository
) : NavigateWithParamsUseCase {

    override fun invoke(
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
        val navHostController = navigationRepository.getNavigationController(id)
            ?: throw IllegalStateException("Navigation controller with id $id not found.")
        //
        // route
        //
        val route = if (args.isNotEmpty()) {
            buildString {
                append(destination.route)
                for (arg in args) append("/$arg")
            }
        } else {
            destination.route
        }
        //
        // navigate with bundle args
        //
        if (bundle != null) {
            val node = navHostController.graph.findNode(route)?.id
            if (node != null) {
                val option = navOptions {
                    if (clearBackStack) {
                        popUpTo(0)
                    } else if (popupDestination != null) {
                        popUpTo(popupDestination.route) {
                            this.inclusive = true
                            this.saveState = saveState
                        }
                    }
                    //
                    // Avoid multiple copies of the same destination when re-selecting the same item
                    //
                    this.launchSingleTop = singleTop
                    //
                    // Restore state when re-selecting a previously selected item
                    //
                    this.restoreState = restoreState
                }
                navHostController.navigate(node, bundle, option, null)
            }
        }
        //
        // navigate with single route
        //
        else {
            navHostController.navigate(route = route) {
                if (clearBackStack) {
                    popUpTo(0)
                } else if (popupDestination != null) {
                    popUpTo(popupDestination.route) {
                        this.inclusive = true
                        this.saveState = saveState
                    }
                }
                //
                // Avoid multiple copies of the same destination when re-selecting the same item
                //
                this.launchSingleTop = singleTop
                //
                // Restore state when re-selecting a previously selected item
                //
                this.restoreState = restoreState
            }
        }
    }
}