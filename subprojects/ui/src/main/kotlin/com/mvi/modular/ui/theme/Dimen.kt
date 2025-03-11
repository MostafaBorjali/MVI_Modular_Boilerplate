package com.mvi.modular.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Immutable
data class Dimen(
    val statusBarHeight: Dp,
    val borderWidth: Dp,
    val bottomSheetMinHeight: Dp,
    val minButtonWidth: Dp,
    val dropdownMenuWidth: Dp,
    val esimCardHeight: Dp,
    val gaugeCardSize: Dp,
    val miniGaugeCardSize: Dp,
    val packageCardHeight: Dp,
    val offersCardSize: Dp,
    val tabSwitchHeight: Dp,
)


val LocalDimen = compositionLocalOf { DefaultDimen }


internal val DefaultDimen = Dimen(
    statusBarHeight = 56.dp,
    borderWidth = 0.5.dp,
    bottomSheetMinHeight = 500.dp,
    minButtonWidth = 100.dp,
    dropdownMenuWidth = 140.dp,
    esimCardHeight = 220.dp,
    gaugeCardSize = 200.dp,
    miniGaugeCardSize = 115.dp,
    packageCardHeight = 220.dp,
    offersCardSize = 160.dp,
    tabSwitchHeight = 40.dp,
)

internal val LargeDimen = Dimen(
    statusBarHeight = 64.dp,
    borderWidth = 0.6.dp,
    bottomSheetMinHeight = 750.dp,
    minButtonWidth = 150.dp,
    dropdownMenuWidth = 180.dp,
    esimCardHeight = 260.dp,
    gaugeCardSize = 250.dp,
    miniGaugeCardSize = 155.dp,
    packageCardHeight = 270.dp,
    offersCardSize = 215.dp,
    tabSwitchHeight = 60.dp
)

internal val SmallDimen = Dimen(
    statusBarHeight = 48.dp,
    borderWidth = 0.3.dp,
    bottomSheetMinHeight = 250.dp,
    minButtonWidth = 60.dp,
    dropdownMenuWidth = 90.dp,
    esimCardHeight = 170.dp,
    gaugeCardSize = 150.dp,
    miniGaugeCardSize = 75.dp,
    packageCardHeight = 180.dp,
    offersCardSize = 125.dp,
    tabSwitchHeight = 30.dp,
)


@Suppress("UnusedReceiverParameter")
val MaterialTheme.dimen: Dimen
    @Composable
    @ReadOnlyComposable
    get() = LocalDimen.current