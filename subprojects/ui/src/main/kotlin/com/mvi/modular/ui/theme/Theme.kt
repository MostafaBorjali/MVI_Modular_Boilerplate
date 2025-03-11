package com.mvi.modular.ui.theme

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.mvi.modular.strings.StringsConstants



private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color.Black,
    surface = Color.Black,
    surfaceTint = Color.Black,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = White600,
    surface = White600,
    surfaceTint = White600,
)


@Composable
fun MviModularTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    SideEffect {
        (view.context as? Activity)?.window?.let { window ->
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    val size = calculateDeviceSize()
    Log.i(
        StringsConstants.TAG,
        "screen-width: ${size.width}, Screen-height: ${size.height}, size: ${size.name}"
    )

    val typography = when (size) {
        is Size.Large -> LargeTypography
        is Size.Medium -> DefaultTypography
        is Size.Small -> SmallTypography
    }

    val dimension = when (size) {
        is Size.Large -> LargeDimen
        is Size.Medium -> DefaultDimen
        is Size.Small -> SmallDimen
    }

    CompositionLocalProvider(
        LocalColors provides MviModularColors,
        LocalTextStyle provides LocalTextStyle.current.merge(fontFamily = RobotoCondensedFontFamily),
        LocalTypography provides typography,
        LocalDimen provides dimension,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}