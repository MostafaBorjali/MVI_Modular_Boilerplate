package com.mvi.android.ui.compose

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalDimen
import com.ammotel.android.ui.theme.LocalTypography


@Composable
fun TabSwitch(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedIndex: Int,
    onChange: (Int) -> Unit
) {
    BoxWithConstraints(
        modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = LocalColors.current.backgroundBezeledButtonSecondaryColor.copy(
                    alpha = 0.24f
                )
            )
            .padding(2.dp)
    ) {
        if (items.isNotEmpty()) {
            val maxWidth = this.maxWidth
            val tabWidth = maxWidth / items.size

            val shadowIndicatorOffset by animateDpAsState(
                targetValue = tabWidth * selectedIndex,
                animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
                label = "indicator offset"
            )

            val indicatorOffset by animateDpAsState(
                targetValue = if (LocalLayoutDirection.current == LayoutDirection.Rtl) {
                    tabWidth * (items.size - 1 - selectedIndex)
                } else {
                    tabWidth * selectedIndex
                },
                animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
                label = "indicator offset"
            )

            // This is for shadow layer matching white background
            Box(
                modifier = Modifier
                    .offset(x = shadowIndicatorOffset)
                    .shadow(4.dp, RoundedCornerShape(10.dp))
                    .width(tabWidth)
                    .fillMaxHeight()
            )

            Row(modifier = Modifier
                .fillMaxWidth()
                .drawWithContent {

                    // This is for setting black text while drawing on white background
                    val padding = 8.dp.toPx()
                    drawRoundRect(
                        topLeft = Offset(x = indicatorOffset.toPx() + padding, padding),
                        size = Size(tabWidth.toPx() - padding * 2, size.height - padding * 2),
                        color = Color.Black,
                        cornerRadius = CornerRadius(x = 8.dp.toPx(), y = 8.dp.toPx()),
                    )

                    drawWithLayer {
                        drawContent()

                        // This is white top rounded rectangle
                        drawRoundRect(
                            topLeft = Offset(x = indicatorOffset.toPx(), 0f),
                            size = Size(tabWidth.toPx(), size.height),
                            color = Color.White,
                            cornerRadius = CornerRadius(x = 8.dp.toPx(), y = 8.dp.toPx()),
                            blendMode = BlendMode.SrcOut
                        )
                    }
                }
            ) {
                items.forEachIndexed { index, text ->
                    Box(
                        modifier = Modifier
                            .width(tabWidth)
                            .fillMaxHeight()
                            .clickable(
                                interactionSource = remember {
                                    MutableInteractionSource()
                                },
                                indication = null,
                                onClick = {
                                    onChange(index)
                                }
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        val style = if (selectedIndex == index) {
                            LocalTypography.current.extraSmall.headline
                        } else {
                            LocalTypography.current.extraSmall.body
                        }
                        Text(
                            text = text,
                            style = style,
                            color = LocalColors.current.blackLabelColorPrimary,
                        )
                    }
                }
            }
        }
    }
}

fun ContentDrawScope.drawWithLayer(block: ContentDrawScope.() -> Unit) {
    with(drawContext.canvas.nativeCanvas) {
        val checkPoint = saveLayer(null, null)
        block()
        restoreToCount(checkPoint)
    }
}

@Preview(showBackground = true)
@Composable
fun TextSwitchPreview() {
    AmmotelTheme {
        val items = remember {
            listOf("Active", "Expired")
        }

        var selectedIndex by remember {
            mutableIntStateOf(0)
        }

        Column {
            TabSwitch(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(LocalDimen.current.tabSwitchHeight),
                items = items,
                selectedIndex = selectedIndex,
                onChange = {
                    selectedIndex = it
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TextSwitchRTLPreview() {
    AmmotelTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            val items = remember {
                listOf("Direct", "QR Scan", "Manual")
            }

            var selectedIndex by remember {
                mutableIntStateOf(0)
            }

            Column {
                TabSwitch(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(LocalDimen.current.tabSwitchHeight),
                    items = items,
                    selectedIndex = selectedIndex,
                    onChange = {
                        selectedIndex = it
                    }
                )
            }
        }
    }
}