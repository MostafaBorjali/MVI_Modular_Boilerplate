package com.mvi.modular.home.screen.explore.state

import com.mvi.modular.error.domain.model.ErrorEvent


internal data class ExploreScreenUiState(
    val loading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorInfo: ErrorEvent? = null,
)