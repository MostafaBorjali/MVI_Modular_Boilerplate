package com.mvi.modular.ui.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mvi.modular.ui.theme.LocalColors
import com.mvi.modular.ui.theme.MviModularTheme


@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    numberOfPages: Int,
    selectedPage: Int = 0,
    selectedColor: Color = LocalColors.current.paginationActiveColor,
    defaultColor: Color = LocalColors.current.paginationDeActiveColor,
    radius: Dp = 5.dp,
    space: Dp = 3.dp,
    animationDurationInMillis: Int = 300,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(radius + space)
    ) {
        repeat(numberOfPages) { currentIndex ->
            PageIndicatorView(
                isSelected = currentIndex == selectedPage,
                selectedColor = selectedColor,
                defaultColor = defaultColor,
                radius = radius,
                animationDurationInMillis = animationDurationInMillis,
            )
        }
    }
}

@Composable
fun PageIndicatorView(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    selectedColor: Color,
    defaultColor: Color,
    radius: Dp,
    animationDurationInMillis: Int,
) {
    val color: Color by animateColorAsState(
        targetValue = if (isSelected) {
            selectedColor
        } else {
            defaultColor
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
        ),
        label = "pageIndicator"
    )

    Canvas(
        modifier = modifier
            .size(
                width = radius,
                height = radius,
            )
    ) {
        drawCircle(
            color = color,
            radius = radius.toPx(),
        )
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun PageIndicatorPreview() {
    MviModularTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PageIndicator(
                numberOfPages = 5,
                selectedPage = 1
            )
        }
    }
}