package com.mvi.modular.home.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.mvi.modular.home.navigation.HomeNavigationItem
import com.mvi.modular.home.navigation.HomeScreenNavigation
import com.mvi.modular.navigation.core.NavigationConstants.HOME_NAVIGATION_ID
import com.mvi.modular.navigation.domain.model.Destination
import com.mvi.modular.navigation.service.NavigationService
import com.mvi.modular.ui.theme.LocalColors
import com.mvi.modular.ui.theme.LocalTypography
import com.mvi.modular.ui.theme.MviModularTheme


@Composable
fun HomeScreen(
    modifier: Modifier,
    navigationService: NavigationService,
) {
    val items = listOf(
        HomeNavigationItem.MyESim,
        HomeNavigationItem.Explore,
        HomeNavigationItem.Profile,
    )

    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomBar(items, selectedIndex) { lastNavigationItem, currentNavigationItem, index ->
                if (selectedIndex != index) {
                    selectedIndex = index
                    navigationService.navigate(
                        id = HOME_NAVIGATION_ID,
                        destination = currentNavigationItem.destination,
                        popupDestination = lastNavigationItem.destination,
                        saveState = true,
                        singleTop = true,
                        restoreState = true,
                    )
                }
            }
        }
    ) { paddingValues ->
        HomeScreenNavigation(
            modifier = Modifier.fillMaxSize(),
            navigationService = navigationService,
            startDestination = Destination.Home.ESim,
            mainPaddingValues = paddingValues,

            )
    }

    BackHandler(selectedIndex != 0) {
        val current = selectedIndex
        selectedIndex = 0
        navigationService.navigate(
            id = HOME_NAVIGATION_ID,
            destination = Destination.Home.ESim,
            popupDestination = items[current].destination,
            saveState = true,
            singleTop = true,
            restoreState = true,
        )
    }
}

@Composable
private fun BottomBar(
    items: List<HomeNavigationItem>,
    selectedIndex: Int,
    onClick: (HomeNavigationItem, HomeNavigationItem, Int) -> Unit
) {
    HorizontalDivider(color = Color.Black)
    NavigationBar(
        containerColor = LocalColors.current.navbarBackgroundColor,
        tonalElevation = 4.dp,
    ) {
        items.forEachIndexed { index, item ->
            CompositionLocalProvider {
                NavigationBarItem(
                    selected = index == selectedIndex,
                    onClick = {
                        onClick(items[selectedIndex], item, index)
                    },
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.destination.route
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = item.title),
                            style = LocalTypography.current.small.caption1,
                        )
                    },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = LocalColors.current.navbarItemColor,
                        selectedTextColor = LocalColors.current.navbarItemColor,
                        unselectedIconColor = LocalColors.current.secondaryColor,
                        unselectedTextColor = LocalColors.current.secondaryColor,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BottomBarLtrPreview() {
    val items = listOf(
        HomeNavigationItem.MyESim,
        HomeNavigationItem.Explore,
        HomeNavigationItem.Profile,
    )
    MviModularTheme {
        BottomBar(items, 0) { _, _, _ -> }
    }
}


@Preview(showBackground = true)
@Composable
fun BottomBarRtlPreview() {
    val items = listOf(
        HomeNavigationItem.MyESim,
        HomeNavigationItem.Explore,
        HomeNavigationItem.Profile,
    )
    MviModularTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            BottomBar(items, 0) { _, _, _ -> }
        }
    }
}