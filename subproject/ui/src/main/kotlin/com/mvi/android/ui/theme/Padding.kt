package com.mvi.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Immutable
data class Padding(
    val little: Dp = 2.5.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 10.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val extraLarge: Dp = 40.dp,
    val horizontalOffset: Dp = 20.dp,
)


val LocalPadding = compositionLocalOf { Padding() }


@Suppress("UnusedReceiverParameter")
val MaterialTheme.padding: Padding
    @Composable
    @ReadOnlyComposable
    get() = LocalPadding.current