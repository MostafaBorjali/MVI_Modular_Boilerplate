package com.ammotel.android.ui.compose

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
fun ESIMCard(
    modifier: Modifier = Modifier,
    name: String,
    code: String,
    iccId: String? = null,
    coverage: String,
    remainingData: String,
    expireDate: String,
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
                ESIMCardHeader(
                    name = name,
                    code = code,
                    icon = icon,
                    textStyle = LocalTypography.current.ax2.headline
                )

                Spacer(modifier = Modifier.height(MaterialTheme.padding.extraSmall))

                Text(
                    text = if (iccId.isNullOrBlank()) {
                        stringResource(id = strings.home_esim_card_group_purchase_title)
                    } else {
                        stringResource(id = strings.home_esim_iccid_title, iccId)
                    },
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

                    ESIMBottomText(
                        modifier = Modifier.weight(1f),
                        title = stringResource(id = strings.general_coverage),
                        text = coverage
                    )

                    ESIMBottomText(
                        modifier = Modifier.weight(1f),
                        title = if (iccId.isNullOrBlank()) {
                            stringResource(id = strings.home_esim_card_esim_number_title)
                        } else {
                            stringResource(id = strings.home_esim_card_remaining_title)
                        },
                        text = remainingData
                    )
                    if (!iccId.isNullOrBlank()) {
                        ESIMBottomText(
                            modifier = Modifier.weight(1f),
                            title = if (isExpired) {
                                stringResource(id = strings.home_esim_card_expired_title)
                            } else {
                                stringResource(id = strings.home_esim_card_expires_in_title)
                            },
                            text = expireDate
                        )
                    }

                }
            }
        }
    }
}


@Composable
fun ESIMBottomText(
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
fun ESIMCardHeader(
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
fun ESIMPreview() {
    AmmotelTheme {
        ESIMCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalDimen.current.esimCardHeight),
            name = "United Arab Emirates",
            code = "TR",
            iccId = "2364534765238976",
            coverage = "United Arab Emirates of Iran Islamic",
            remainingData = "2 GB",
            isExpired = false,
            expireDate = "2023/19/08",
            icon = drawables.ic_esim
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardHeaderPreview() {
    AmmotelTheme {
        ESIMCardHeader(
            code = "AE",
            name = "United Arab Emirates",
            icon = drawables.ic_data_transfer
        )
    }
}