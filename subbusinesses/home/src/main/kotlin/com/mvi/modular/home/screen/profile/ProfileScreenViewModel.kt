package com.mvi.modular.home.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.modular.home.screen.profile.event.ProfileScreenEvent
import com.mvi.modular.home.screen.profile.state.ProfileScreenUiState
import com.mvi.modular.home.screen.profile.state.ProfileSettingItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


internal class ProfileScreenViewModel(

    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileScreenUiState())
    val uiState = _state.asStateFlow()

    fun onEvent(event: ProfileScreenEvent) {
        when (event) {

            is ProfileScreenEvent.ProfileSettingItemClicked -> {
                when (event.item) {
                    ProfileSettingItem.ChangeServer -> {
                        viewModelScope.launch(dispatcher) {
                            // changeServer()
                        }

                    }
                }
            }
        }
    }


}