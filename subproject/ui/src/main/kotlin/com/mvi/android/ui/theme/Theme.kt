package com.mvi.android.ui.theme

import android.app.Activity
import android.util.Log
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.ammotel.android.strings.StringsConstants
import com.ammotel.android.ui.core.ApplicationType.AMMOTEL
import com.ammotel.android.ui.core.ApplicationType.DARK
import com.ammotel.android.ui.core.ApplicationType.MOBIKOR


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
fun MviModularBoilerplateTheme(
    themeType: String = LIGHT.name,
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeType) {
        LIGHT.name -> LightColorScheme
        else -> DarkColorScheme
    }

    val view = LocalView.current
    SideEffect {
        (view.context as? Activity)?.window?.let { window ->
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                themeType != DARK.name
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
    val localColors = MviModularBoilerplateColors
    }
    CompositionLocalProvider(
        LocalColors provides localColors,
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