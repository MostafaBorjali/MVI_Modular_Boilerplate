package com.mvi.modular.home.screen.esim.state


import com.mvi.modular.base.functional.Event
import com.mvi.modular.home.navigation.HomeNavigationItem


internal data class ESimScreenUiState(val bottomBarEvent: Event<HomeNavigationItem>? = null)
