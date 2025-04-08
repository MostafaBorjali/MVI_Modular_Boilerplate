package com.mvi.modular.home.screen.shop

import androidx.lifecycle.ViewModel
import com.mvi.modular.home.screen.shop.event.ShopScreenEvent
import com.mvi.modular.home.screen.shop.state.ShopScreenUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


internal class ShopScreenViewModel(private val dispatcher: CoroutineDispatcher) : ViewModel() {

    private val _state = MutableStateFlow(ShopScreenUiState(loading = true))
    val uiState = _state.asStateFlow()

    fun onEvent(event: ShopScreenEvent) {

    }


}