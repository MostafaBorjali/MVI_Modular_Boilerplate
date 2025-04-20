package com.mvi.modular.home.screen.explore

import androidx.lifecycle.ViewModel
import com.mvi.modular.home.screen.explore.event.ExploreScreenEvent
import com.mvi.modular.home.screen.explore.state.ExploreScreenUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


internal class ExploreScreenViewModel(private val dispatcher: CoroutineDispatcher) : ViewModel() {

    private val _state = MutableStateFlow(ExploreScreenUiState(loading = true))
    val uiState = _state.asStateFlow()

    fun onEvent(event: ExploreScreenEvent) {
        when (event) {
            is ExploreScreenEvent.Load -> {}
            is ExploreScreenEvent.Refresh -> {}
            is ExploreScreenEvent.Search -> {}
            is ExploreScreenEvent.SelectCountry -> {}
        }

    }


}