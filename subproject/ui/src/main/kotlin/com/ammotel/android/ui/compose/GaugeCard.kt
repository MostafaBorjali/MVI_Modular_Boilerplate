package com.ammotel.android.ui.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ammotel.android.strings.core.strings
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalPadding
import com.ammotel.android.ui.theme.LocalTypography
import com.ammotel.android.utils.date.daysLeft
import com.ammotel.android.utils.string.mb2String


@Composable
fun GaugeCard(
    modifier: Modifier,
    endTime: Long,
    endTimeStyle: TextStyle = LocalTypography.current.xExtraLarge.subhead,
    endTimePadding: Dp = LocalPadding.current.extraLarge,
    volume: Long,
    volumeStyle: TextStyle = LocalTypography.current.ax1.headline,
    volumePadding: Dp = LocalPadding.current.large,
    maxVolume: Long,
    maxVolumeText: Long,
    enable: Boolean,
    stroke: Dp = 18.dp,
    showMinAndMax: Boolean = true,
) {
    val colors = LocalColors.current
    val sweepAngle = (240 * volume) / maxVolume

    Box(modifier = modifier) {

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = LocalPadding.current.extraSmall)
        ) {
            drawArc(
                color = colors.secondaryColor.copy(alpha = 0.15f),
                startAngle = 150f,
                sweepAngle = 240f,
                useCenter = false,
                style = Stroke(
                    width = stroke.toPx(),
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                )
            )

            if (enable) {
                drawArc(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to colors.primaryGradiantStart,
                            0.6f to colors.primaryGradiantEnd
                        )
                    ),
                    startAngle = 150f,
                    sweepAngle = sweepAngle.toFloat(),
                    useCenter = false,
                    style = Stroke(
                        width = stroke.toPx(),
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round,
                    )
                )
            } else {
                drawArc(
                    color = colors.lineColor,
                    startAngle = 150f,
                    sweepAngle = sweepAngle.toFloat(),
                    useCenter = false,
                    style = Stroke(
                        width = stroke.toPx(),
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round,
                    )
                )
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(bottom = endTimePadding),
            text = stringResource(id = strings.general_days_left, endTime.daysLeft()),
            style = endTimeStyle.copy(
                color = colors.labelColorSecondary.copy(alpha = 0.58f)
            ),
            textAlign = TextAlign.Center,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(top = volumePadding),
            text = mb2String(volume, false),
            style = volumeStyle,
            textAlign = TextAlign.Center,
        )

        if (showMinAndMax) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = LocalPadding.current.small),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    modifier = Modifier.padding(start = LocalPadding.current.small),
                    text = "0",
                    style = LocalTypography.current.large.body,
                )

                Text(
                    text = mb2String(maxVolumeText, false),
                    style = LocalTypography.current.large.body,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GaugeCardPreview() {
    AmmotelTheme {
        GaugeCard(
            modifier = Modifier
                .size(200.dp)
                .padding(start = 10.dp, top = 10.dp, end = 10.dp),
            endTime = System.currentTimeMillis(),
            volume = 4853,
            maxVolume = 5_120,
            maxVolumeText = 5120,
            enable = true,
        )
    }
}


@Composable
fun LoadingGaugeCard(
    modifier: Modifier,
    centerBoxWidth: Dp = 100.dp,
    centerBoxHeight: Dp = 20.dp,
    stroke: Dp = 18.dp,
) {
    Box(modifier = modifier) {
        val colors = LocalColors.current
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = LocalPadding.current.extraSmall)
        ) {
            drawArc(
                color = colors.lineColor,
                startAngle = 150f,
                sweepAngle = 240f,
                useCenter = false,
                style = Stroke(
                    width = stroke.toPx(),
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                )
            )
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(centerBoxWidth)
                    .height(centerBoxHeight)
                    .background(LocalColors.current.lineColor),
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .width(centerBoxWidth)
                    .height(centerBoxHeight)
                    .background(LocalColors.current.lineColor),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingGaugeCardPreview() {
    AmmotelTheme {
        LoadingGaugeCard(
            modifier = Modifier
                .size(200.dp)
                .padding(start = 10.dp, top = 10.dp, end = 10.dp),
        )
    }
}