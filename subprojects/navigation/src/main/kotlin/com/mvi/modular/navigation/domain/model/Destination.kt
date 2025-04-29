package com.mvi.modular.navigation.domain.model

import com.mvi.modular.navigation.core.NavigationConstants.MAIN_SCREEN
import com.mvi.modular.navigation.core.NavigationConstants.EXPLORE_SCREEN
import com.mvi.modular.navigation.core.NavigationConstants.INTRO_SCREEN
import com.mvi.modular.navigation.core.NavigationConstants.PROFILE_SCREEN
import com.mvi.modular.navigation.core.NavigationConstants.SPLASH_SCREEN
import kotlinx.serialization.Serializable

@Serializable
sealed class Destination(val route: String) {

    /**
     * Represent splash screen destination
     */
    @Serializable
    data object Splash : Destination(SPLASH_SCREEN)

    /**
     * Represent intro screen destination
     */
    @Serializable
    data object Intro : Destination(INTRO_SCREEN)

    /**
     * Represent home flow destination
     */

    sealed class Home(route: String) : Destination(route) {

        /**
         * Represent nav host
         *//*
        @Serializable
        data object Main : Destination(HOME_SCREEN)*/

        /**
         * Represent main screen destination
         */
        @Serializable
        data object Main : Destination(MAIN_SCREEN)

        /**
         * Represent explore screen destination
         */
        @Serializable
        data object Explore : Destination(EXPLORE_SCREEN)

        /**
         * Represent profile screen destination
         */
        @Serializable
        data object Profile : Destination(PROFILE_SCREEN)
    }
}
