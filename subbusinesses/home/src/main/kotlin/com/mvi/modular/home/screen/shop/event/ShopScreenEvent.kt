package com.mvi.modular.home.screen.shop.event



sealed interface ShopScreenEvent {

    /**
     *
     */
    data object Load : ShopScreenEvent

    /**
     *
     */
    data object Refresh : ShopScreenEvent

    /**
     *
     */
    data class Search(val query: String) : ShopScreenEvent

    /**
     *
     */
    data object SelectCountry : ShopScreenEvent
}