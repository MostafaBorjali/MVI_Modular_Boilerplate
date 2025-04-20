package com.mvi.modular.home.screen.home.state


import com.mvi.modular.base.functional.Event
import com.mvi.modular.error.domain.model.ErrorEvent
import com.mvi.modular.home.navigation.HomeNavigationItem
import com.mvi.modular.movie.domain.model.MovieDto


internal data class HomeScreenUiState(
    val bottomBarEvent: Event<HomeNavigationItem>? = null,
    val loading: Boolean = false,
    val topMovies: List<MovieDto>? = null,
    val getTopMoviesError: ErrorEvent? = null,
)
