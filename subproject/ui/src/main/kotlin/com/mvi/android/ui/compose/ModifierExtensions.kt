package com.mvi.android.ui.compose

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize


/**
 * Update target modifier conditionally
 *
 * @receiver target modifier that wants to apply on
 * @param condition
 * @param ifTrue
 * @param ifFalse
 * @return target modifier that conditions apply on
 */
inline fun Modifier.conditional(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: Modifier.() -> Modifier = { this },
): Modifier = if (condition) {
    then(ifTrue(Modifier))
} else {
    then(ifFalse(Modifier))
}


/**
 * Mirror the [Modifier] horizontally
 *
 * @receiver [Modifier] that wants to mirror
 * @return horizontally mirrored [Modifier]
 */
fun Modifier.mirrorHorizontally(): Modifier = this.scale(scaleX = -1f, scaleY = 1f)


/**
 * Mirror the [Modifier] vertically
 *
 * @receiver [Modifier] that wants to mirror
 * @return vertically mirrored [Modifier]
 */
fun Modifier.mirrorVertically(): Modifier = this.scale(scaleX = 1f, scaleY = -1f)


/**
 * Shimmer effect
 */
fun Modifier.shimmerEffect(
    colors: ShimmerEffectColors = ShimmerEffectDefaults.colors(),
    duration: Int = ShimmerEffectDefaults.DURATION,
): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "shimmerEffect")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = duration)
        ),
        label = "shimmerEffectOffset"
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                colors.start,
                colors.middle,
                colors.end,
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}

@Immutable
class ShimmerEffectColors(
    val start: Color,
    val middle: Color,
    val end: Color,
)

@Immutable
object ShimmerEffectDefaults {

    const val DURATION: Int = 1000

    fun colors(): ShimmerEffectColors {
        return ShimmerEffectColors(
            start = Color(0xFFE4E2E2),
            middle = Color(0xFFC0BBBB),
            end = Color(0xFFE4E2E2),
        )
    }

    fun colors(
        start: Color = Color.Unspecified,
        middle: Color = Color.Unspecified,
        end: Color = Color.Unspecified,
    ): ShimmerEffectColors {
        val defaults = colors()
        return ShimmerEffectColors(
            start = start.takeOrElse { defaults.start },
            middle = middle.takeOrElse { defaults.middle },
            end = end.takeOrElse { defaults.end },
        )
    }
}