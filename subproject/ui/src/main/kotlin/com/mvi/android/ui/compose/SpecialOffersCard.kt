package com.mvi.android.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.ammotel.android.ui.extensions.toImageResource
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalDimen
import com.ammotel.android.ui.theme.LocalTypography
import com.ammotel.android.ui.theme.padding


@Composable
fun SpecialOffersCard(
    modifier: Modifier = Modifier,
    data: String,
    code: String,
    days: String,
    price: String,
    onClick: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = LocalColors.current.backgroundCardPrimary,
            contentColor = LocalColors.current.whiteLabelColorPrimary
        ),
        shape = MaterialTheme.shapes.large,
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            LocalColors.current.cardAngledGradientStart,
                            LocalColors.current.cardAngledGradientEnd
                        ),
                        start = Offset.Zero,
                        end = Offset.Infinite,
                    )
                )
        ) {
            Image(
                painter = painterResource(id = code.toImageResource()),
                contentDescription = data,
                alpha = 0.1f,
                modifier = Modifier
                    .align(alignment = Alignment.BottomStart)
                    .mirrorHorizontally()
            )

            Column(
                modifier = Modifier
                    .padding(MaterialTheme.padding.small)
                    .fillMaxSize()
            ) {
                Text(
                    text = data,
                    style = LocalTypography.current.ax3.headline,
                    modifier = Modifier
                )

                Text(
                    text = days,
                    style = LocalTypography.current.large.headline,
                    modifier = Modifier
                )
            }

            Text(
                text = price,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = LocalTypography.current.ax3.headline,
                modifier = Modifier
                    .padding(MaterialTheme.padding.small)
                    .align(alignment = Alignment.BottomEnd)
            )
        }
    }
}

@Preview
@Composable
fun SpecialOffersCardPreview() {
    AmmotelTheme {
        SpecialOffersCard(
            modifier = Modifier.size(LocalDimen.current.offersCardSize),
            data = "3GB",
            code = "VN",
            days = "7Days",
            price = "$4"
        )
    }
}