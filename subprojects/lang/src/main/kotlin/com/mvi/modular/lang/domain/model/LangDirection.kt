package com.mvi.modular.lang.domain.model


internal sealed class LangDirection

internal data object Ltr : LangDirection()
internal data object Rtl : LangDirection()
