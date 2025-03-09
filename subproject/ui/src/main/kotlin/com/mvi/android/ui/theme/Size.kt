package com.mvi.android.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration


internal sealed class Size(val width: Int, val height: Int, val name: String) {

    /**
     *
     */
    class Small(width: Int, height: Int) : Size(width, height, "Small")

    /**
     *
     */
    class Medium(width: Int, height: Int) : Size(width, height, "Medium")

    /**
     *
     */
    class Large(width: Int, height: Int) : Size(width, height, "Large")
}

/**
 *
 */
@Composable
internal fun calculateDeviceSize(): Size {
    val width = LocalConfiguration.current.screenWidthDp
    val height = LocalConfiguration.current.screenHeightDp

    return if (height < 640) {
        Size.Small(width, height)
    } else if (width > 600) {
        Size.Large(width, height)
    } else {
        Size.Medium(width, height)
    }
}