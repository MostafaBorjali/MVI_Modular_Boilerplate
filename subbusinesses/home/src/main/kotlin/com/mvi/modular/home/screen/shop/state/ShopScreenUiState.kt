package com.mvi.modular.home.screen.shop.state

import com.mvi.modular.error.domain.model.ErrorEvent


internal data class ShopScreenUiState(
    val loading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorInfo: ErrorEvent? = null,
)