package com.mvi.modular.home.screen.esim.event


internal sealed interface ESimScreenEvent {
    /**
     *
     */
    data object Refresh : ESimScreenEvent

}