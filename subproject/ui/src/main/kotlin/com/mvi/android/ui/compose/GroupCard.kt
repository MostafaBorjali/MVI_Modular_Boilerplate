package com.mvi.android.ui.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ammotel.android.strings.core.strings
import com.ammotel.android.ui.core.drawables
import com.ammotel.android.ui.extensions.toImageResource
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalDimen
import com.ammotel.android.ui.theme.LocalTypography
import com.ammotel.android.ui.theme.padding
import com.ammotel.android.utils.string.toFlagEmoji


@Composable
fun GroupCard(
    modifier: Modifier = Modifier,
    name: String,
    code: String,
    coverage: String,
    eSimCount: String,
    isExpired: Boolean,
    @DrawableRes icon: Int = drawables.ic_esim,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (isExpired) {
                LocalColors.current.borderIconActiveColor
            } else {
                LocalColors.current.backgroundCardPrimary
            },
            contentColor = LocalColors.current.whiteLabelColorPrimary
        ),
        shape = MaterialTheme.shapes.large,
    ) {
        Box(
            modifier = Modifier.padding(top = MaterialTheme.padding.medium)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(alignment = Alignment.BottomEnd),
                painter = painterResource(id = code.toImageResource()),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                alpha = 0.1f,
            )

            Column(
                modifier = Modifier.padding(horizontal = MaterialTheme.padding.medium)
            ) {
                GroupCardHeader(
                    name = name,
                    code = code,
                    icon = icon,
                    textStyle = LocalTypography.current.ax2.headline
                )

                Spacer(modifier = Modifier.height(MaterialTheme.padding.extraSmall))

                Text(
                    text = stringResource(id = strings.home_esim_card_group_purchase_title),
                    style = LocalTypography.current.large.subhead,
                    color = LocalColors.current.labelColorQuaternary,
                )

                HorizontalDivider(
                    modifier = Modifier.padding(top = MaterialTheme.padding.large)
                )

                Row(
                    modifier = Modifier
                        .padding(top = MaterialTheme.padding.medium)
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    GroupBottomText(
                        modifier = Modifier.weight(1f),
                        title = stringResource(id = strings.general_coverage),
                        text = coverage
                    )

                    GroupBottomText(
                        modifier = Modifier.weight(1f),
                        title = stringResource(id = strings.home_esim_card_esim_number_title),
                        text = eSimCount
                    )

                }
            }
        }
    }
}


@Composable
fun GroupBottomText(
    modifier: Modifier = Modifier,
    title: String,
    text: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.padding(top = MaterialTheme.padding.small),
            text = title,
            style = LocalTypography.current.small.body,
            color = LocalColors.current.whiteLabelColorSecondary,
        )

        Text(
            modifier = Modifier.padding(top = MaterialTheme.padding.small),
            text = text,
            textAlign = TextAlign.Center,
            style = LocalTypography.current.small.body,
            color = LocalColors.current.whiteLabelColorSecondary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }

}

@Composable
fun GroupCardHeader(
    modifier: Modifier = Modifier,
    name: String,
    code: String,
    textStyle: TextStyle? = null,
    iconSize: Dp = 36.dp,
    @DrawableRes icon: Int = drawables.ic_esim,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "$name ${code.toFlagEmoji()}",
            style = textStyle ?: LocalTypography.current.ax2.headline,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Image(
            modifier = Modifier
                .size(iconSize)
                .weight(0.15f),
            painter = painterResource(id = icon),
            colorFilter = ColorFilter.tint(LocalColors.current.whiteLabelColorPrimary),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GroupPreview() {
    AmmotelTheme {
        GroupCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalDimen.current.esimCardHeight),
            name = "United Arab Emirates",
            code = "TR",
            coverage = "United Arab Emirates of Iran Islamic",
            eSimCount = "2",
            isExpired = false,
            icon = drawables.ic_esim
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GroupHeaderPreview() {
    AmmotelTheme {
        ESIMCardHeader(
            code = "AE",
            name = "United Arab Emirates",
            icon = drawables.ic_data_transfer
        )
    }
}