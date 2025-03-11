package com.mvi.modular.deeplink.domain.model

import com.mvi.modular.navigation.domain.model.Destination


data class Deeplink(
    val destination: Destination,
    val subDestination: Destination?,
    val args: List<String>?,
    val needLogin: Boolean,
)