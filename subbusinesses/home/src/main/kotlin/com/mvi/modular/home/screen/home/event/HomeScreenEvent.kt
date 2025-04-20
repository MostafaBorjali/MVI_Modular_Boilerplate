package com.mvi.modular.home.screen.home.event


internal sealed interface HomeScreenEvent {
    /**
     *
     */
    data object Refresh : HomeScreenEvent
    data class LoadTopMovies(val pageNumber: Int) : HomeScreenEvent

}