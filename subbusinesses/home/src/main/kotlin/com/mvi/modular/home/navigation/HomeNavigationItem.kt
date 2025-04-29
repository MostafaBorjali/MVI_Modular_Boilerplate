package com.mvi.modular.home.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mvi.modular.navigation.domain.model.Destination
import com.mvi.modular.strings.core.strings
import com.mvi.modular.ui.core.drawables


internal sealed class HomeNavigationItem(
    val destination: Destination,
    @DrawableRes val icon: Int,
    @StringRes val title: Int
) {
    data object Home : HomeNavigationItem(
        Destination.Home.Main,
        drawables.ic_home,
        strings.home_title
    )

    data object Explore : HomeNavigationItem(
        Destination.Home.Explore,
        drawables.ic_search,
        strings.home_explore_title
    )

    data object Profile : HomeNavigationItem(
        Destination.Home.Profile,
        drawables.ic_nav_bar_profile,
        strings.home_profile_title
    )
}