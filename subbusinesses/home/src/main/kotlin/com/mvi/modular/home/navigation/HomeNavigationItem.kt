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
    data object MyESim : HomeNavigationItem(
        Destination.Home.ESim,
        drawables.ic_nav_bar_esim,
        strings.home_my_esim_title
    )

    data object Shop : HomeNavigationItem(
        Destination.Home.Shop,
        drawables.ic_nav_bar_shop,
        strings.home_shop_title
    )

    data object Profile : HomeNavigationItem(
        Destination.Home.Profile,
        drawables.ic_nav_bar_profile,
        strings.home_profile_title
    )
}