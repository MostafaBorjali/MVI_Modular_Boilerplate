package com.mvi.modular.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mvi.modular.home.screen.esim.ESimScreen
import com.mvi.modular.home.screen.profile.ProfileScreen
import com.mvi.modular.home.screen.shop.ShopScreen
import com.mvi.modular.navigation.core.NavigationConstants
import com.mvi.modular.navigation.domain.model.Destination
import com.mvi.modular.navigation.service.NavigationService


@Composable
internal fun HomeScreenNavigation(
    modifier: Modifier,
    navigationService: NavigationService,
    startDestination: Destination,
    mainPaddingValues: PaddingValues
) {
    val mainNavigationController = rememberNavController()
    navigationService.initApplicationNavigationController(
        id = NavigationConstants.HOME_NAVIGATION_ID,
        navHostController = mainNavigationController
    )

    NavHost(
        modifier = modifier,
        navController = mainNavigationController,
        startDestination = startDestination.route
    ) {
        //
        // Home screen
        //
        composable(Destination.Home.ESim.route) {
            // val viewModel: ESimScreenViewModel = koinViewModel()
            ESimScreen()
        }
        //
        // Shop screen
        //
        composable(Destination.Home.Shop.route) {
            // val viewModel: ShopScreenViewModel = koinViewModel()
            ShopScreen()
        }
        //
        // Profile screen
        //
        composable(Destination.Home.Profile.route) {
            //val viewModel: ProfileScreenViewModel = koinViewModel()
            ProfileScreen()
        }
    }
}