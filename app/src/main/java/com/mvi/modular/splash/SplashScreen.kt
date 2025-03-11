package com.mvi.modular.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.mvi.modular.core.AppConstants.SPLASH_SCREEN_TIME
import com.mvi.modular.ui.core.drawables
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    modifier: Modifier,
    onReady: () -> Unit = {}
) {
    LaunchedEffect(key1 = true) {
        delay(SPLASH_SCREEN_TIME)
        onReady()
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = drawables.ic_successful),
            contentDescription = "logo"
        )
    }
}