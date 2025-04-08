package com.mvi.modular.home.screen.esim

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.modular.home.screen.esim.event.ESimScreenEvent
import com.mvi.modular.home.screen.esim.state.ESimScreenUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


internal class ESimScreenViewModel(private val dispatcher: CoroutineDispatcher) : ViewModel() {

    private val _state = MutableStateFlow(ESimScreenUiState())
    val uiState = _state.asStateFlow()


    fun onEvent(event: ESimScreenEvent) {
        when (event) {

            is ESimScreenEvent.Refresh -> {
                viewModelScope.launch(dispatcher) {

                }
            }

        }
    }

}