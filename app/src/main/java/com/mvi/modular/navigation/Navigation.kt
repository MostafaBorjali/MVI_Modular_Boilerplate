package com.mvi.modular.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mvi.modular.home.screen.HomeScreen
import com.mvi.modular.intro.screen.IntroScreen
import com.mvi.modular.intro.screen.IntroScreenViewModel
import com.mvi.modular.navigation.core.NavigationConstants.APPLICATION_NAVIGATION_ID
import com.mvi.modular.navigation.domain.model.Destination
import com.mvi.modular.navigation.service.NavigationService
import com.mvi.modular.persist.core.PersistConstants.KEY_IS_INTRO_SEEN
import com.mvi.modular.persist.service.PersistService
import com.mvi.modular.splash.SplashScreen
import com.mvi.modular.ui.theme.LocalColors
import org.koin.androidx.compose.koinViewModel


@Composable
fun MviModularNavigation(
    modifier: Modifier,
    navigationService: NavigationService,
    persistService: PersistService,
    startDestination: Destination
) {
    val applicationNavigationController = rememberNavController()
    navigationService.initApplicationNavigationController(
        id = APPLICATION_NAVIGATION_ID,
        navHostController = applicationNavigationController
    )

    NavHost(
        modifier = modifier,
        navController = applicationNavigationController,
        startDestination = startDestination.route
    ) {
        //
        // Splash screen
        //
        composable(Destination.Splash.route) {
            SplashScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LocalColors.current.surfaceZ1Color),
            ) {
                val destination = if (persistService.getBoolean(KEY_IS_INTRO_SEEN) == true) {
                    Destination.Home.Main
                } else {
                    Destination.Intro
                }
                navigationService.navigate(
                    id = APPLICATION_NAVIGATION_ID,
                    destination = destination,
                    popupDestination = Destination.Splash,
                    singleTop = true,
                )
            }
        }
        //
        // Intro screen
        //
        composable(Destination.Intro.route) {
            val viewModel: IntroScreenViewModel = koinViewModel()
            IntroScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = viewModel.uiState,
                onEvent = viewModel::event
            ) {
                navigationService.navigate(
                    id = APPLICATION_NAVIGATION_ID,
                    destination = Destination.Home.Main,
                    popupDestination = Destination.Intro,
                    singleTop = true,
                )
            }
        }
        //
        // Home screen with navigation bar
        //
        composable(Destination.Home.Main.route) {
            val viewModel: IntroScreenViewModel = koinViewModel()
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                navigationService = navigationService,
            )
        }

    }
}