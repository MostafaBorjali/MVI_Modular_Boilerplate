package com.mvi.modular.home.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.ErrorEvent
import com.mvi.modular.error.service.ErrorService
import com.mvi.modular.home.screen.home.event.HomeScreenEvent
import com.mvi.modular.home.screen.home.event.HomeScreenEvent.LoadTopMovies
import com.mvi.modular.home.screen.home.event.HomeScreenEvent.Refresh
import com.mvi.modular.home.screen.home.state.HomeScreenUiState
import com.mvi.modular.lang.service.LanguageService
import com.mvi.modular.movie.service.MoviesService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


internal class HomeScreenViewModel(
    private val moviesService: MoviesService,
    private val languageService: LanguageService,
    private val errorService: ErrorService,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenUiState())
    val uiState = _state.asStateFlow()


    fun onEvent(event: HomeScreenEvent) {
        when (event) {

            is Refresh -> {
                viewModelScope.launch(dispatcher) {

                }
            }

            is LoadTopMovies -> {
                getTopMovies()

            }

        }
    }

    private fun getTopMovies() {
        viewModelScope.launch(dispatcher) {
            when (val response = moviesService.getListOfMovies(
                pageNumber = 1,
                lang = "${languageService.getCurrentLanguage().languageCode}-" +
                        languageService.getCurrentLanguage().countryCode
            )) {
                is Either.Success -> {
                    _state.update { current ->
                        current.copy(
                            loading = false,
                            topMovies = response.data,
                            getTopMoviesError = null,
                        )
                    }

                }

                is Either.Error -> {
                    _state.update { current ->
                        current.copy(
                            loading = false,
                            getTopMoviesError = ErrorEvent(
                                errorService.convert(response.error)
                            ),
                        )
                    }
                }
            }
        }
    }

}