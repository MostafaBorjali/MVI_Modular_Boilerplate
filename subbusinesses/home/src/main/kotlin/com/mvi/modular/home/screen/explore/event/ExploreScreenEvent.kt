package com.mvi.modular.home.screen.explore.event


sealed interface ExploreScreenEvent {

    /**
     *
     */
    data object Load : ExploreScreenEvent

    /**
     *
     */
    data object Refresh : ExploreScreenEvent

    /**
     *
     */
    data class Search(val query: String) : ExploreScreenEvent

    /**
     *
     */
    data object SelectCountry : ExploreScreenEvent
}