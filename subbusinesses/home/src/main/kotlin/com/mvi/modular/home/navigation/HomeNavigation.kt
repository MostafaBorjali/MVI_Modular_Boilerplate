package com.mvi.modular.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mvi.modular.home.screen.explore.ExploreScreen
import com.mvi.modular.home.screen.home.HomeScreen
import com.mvi.modular.home.screen.home.HomeScreenViewModel
import com.mvi.modular.home.screen.profile.ProfileScreen
import com.mvi.modular.navigation.core.NavigationConstants
import com.mvi.modular.navigation.domain.model.Destination
import com.mvi.modular.navigation.service.NavigationService
import org.koin.androidx.compose.koinViewModel


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
            val viewModel: HomeScreenViewModel = koinViewModel()
            HomeScreen(
                state = viewModel.uiState,
                onEvent = viewModel::onEvent,
            )
        }
        //
        // Explore screen
        //
        composable(Destination.Home.Explore.route) {
            // val viewModel: ShopScreenViewModel = koinViewModel()
            ExploreScreen()
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