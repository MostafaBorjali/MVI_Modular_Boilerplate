package com.mvi.modular.navigation.service

import android.os.Bundle
import androidx.navigation.NavHostController
import com.mvi.modular.navigation.domain.model.Destination


interface NavigationService {

    /**
     * Initialize application navigation service
     * It must call at first after application create NavigationHostController
     *
     * @param id Target navigation accessibility id
     * @param navHostController Application navigation host controller
     */
    fun initApplicationNavigationController(
        id: Long,
        navHostController: NavHostController
    )

    /**
     * Call target block with application navigation host controller
     *
     * @param id Target navigation accessibility id
     * @param block Target navigation logic
     * @throws IllegalStateException throw if navigation service not initialize
     */
    fun navigate(
        id: Long,
        block: (NavHostController) -> Unit
    )

    /**
     * Navigate to target destination with parameters
     *
     * @param id Target navigation accessibility id
     * @param destination Target destination to navigate
     * @param popupDestination Target destination to popup
     * @param singleTop Start destination as a singleTon
     * @param restoreState Start destination with restore state option
     * @param clearBackStack Clear all back stack, if set this flag true pop destination will ignore
     * @throws IllegalStateException throw if navigation service not initialize
     */
    fun navigate(
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

    /**
     * Pop back stack to target destination
     *
     * @param id Target navigation accessibility id
     * @param destination Target destination to pop backstack
     * @param inclusive Whether the given destination should also be popped.
     */
    fun pop(
        id: Long,
        destination: Destination,
        inclusive: Boolean = false
    )

    /**
     * Pop back stack
     *
     * @param id Target navigation accessibility id
     */
    fun pop(
        id: Long
    )
}