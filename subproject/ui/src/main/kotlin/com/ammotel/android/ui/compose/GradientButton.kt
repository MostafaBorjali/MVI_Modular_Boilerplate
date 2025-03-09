package com.ammotel.android.ui.compose

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
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalTypography


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
                                    0.0f to LocalColors.current.buttonPrimaryGradiantStart,
                                    0.8f to LocalColors.current.buttonPrimaryGradiantEnd
                                )
                            )
                        )
                    },
                    ifFalse = {
                        background(LocalColors.current.backgroundBezeledButtonSecondaryColor)
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
                    color = LocalColors.current.buttonGradiantTextColor,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GradientButtonPreview() {
    AmmotelTheme {
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
    AmmotelTheme {
        GradientButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            text = "Ok",
            loading = true
        )
    }
}