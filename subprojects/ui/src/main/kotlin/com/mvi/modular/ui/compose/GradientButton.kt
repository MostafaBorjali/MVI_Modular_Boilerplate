package com.mvi.modular.ui.compose

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mvi.modular.ui.theme.LocalColors
import com.mvi.modular.ui.theme.LocalTypography
import com.mvi.modular.ui.theme.MviModularTheme


@Composable
fun GradientButton(
    modifier: Modifier,
    text: String,
    cornerRadius: Dp = 10.dp,
    enable: Boolean = true,
    loading: Boolean = false,
    onClick: () -> Unit = {}
) {
    val animateCornerRadius by animateDpAsState(
        targetValue = if (loading) 50.dp else cornerRadius,
        label = ""
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier
                .conditional(
                    condition = loading,
                    ifTrue = {
                        wrapContentWidth()
                    },
                    ifFalse = {
                        fillMaxWidth()
                    }
                )
                .clip(shape = RoundedCornerShape(animateCornerRadius))
                .conditional(
                    condition = enable && !loading,
                    ifTrue = {
                        background(
                            Brush.horizontalGradient(
                                colorStops =
                                    arrayOf(
                                        0.0f to LocalColors.current.primaryGradiantStart,
                                        0.8f to LocalColors.current.primaryGradiantEnd
                                    )
                            )
                        )
                    },
                    ifFalse = {
                        background(LocalColors.current.primaryColor)
                    }
                )
                .animateContentSize(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            ),
            enabled = enable,
            onClick = onClick
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.DarkGray
                )
            } else {
                Text(
                    text = text,
                    style = LocalTypography.current.large.body,
                    color = LocalColors.current.whiteLabelColorPrimary,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GradientButtonPreview() {
    MviModularTheme {
        GradientButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            text = "Ok",
            loading = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GradientButtonLoginPreview() {
    MviModularTheme {
        GradientButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            text = "Ok",
            loading = true
        )
    }
}