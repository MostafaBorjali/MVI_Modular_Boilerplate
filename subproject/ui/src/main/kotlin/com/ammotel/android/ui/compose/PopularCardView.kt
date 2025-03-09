package com.ammotel.android.ui.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ammotel.android.strings.core.strings
import com.ammotel.android.ui.core.drawables
import com.ammotel.android.ui.extensions.toImageResource
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalTypography
import com.ammotel.android.ui.theme.padding


@Composable
fun PopularCardView(
    modifier: Modifier,
    name: String,
    code: String,
    description: String,
    @DrawableRes icon: Int = drawables.ic_esim,
    onClick: (() -> Unit)? = null,
) {
    Card(
        modifier = modifier.conditional(
            condition = onClick != null,
            ifTrue = {
                clickable(onClick = onClick!!)
            }
        ),
        colors = CardDefaults.cardColors(
            containerColor = LocalColors.current.backgroundCardPrimary,
            contentColor = LocalColors.current.whiteLabelColorPrimary,
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = MaterialTheme.padding.small)
        ) {
            Image(
                modifier = Modifier.align(Alignment.BottomEnd),
                painter = painterResource(id = code.toImageResource()),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )

            Column(
                modifier = modifier.padding(horizontal = MaterialTheme.padding.small)
            ) {
                ESIMCardHeader(
                    name = name,
                    code = code,
                    textStyle = LocalTypography.current.medium.headline,
                    iconSize = 24.dp,
                    icon = icon,
                )
            }

            Text(
                text = description,
                style = LocalTypography.current.extraSmall.subhead,
                modifier = Modifier
                    .align(alignment = Alignment.BottomStart)
                    .padding(
                        horizontal = MaterialTheme.padding.small,
                        vertical = MaterialTheme.padding.medium
                    )
            )
        }
    }
}


@Preview(showBackground = false)
@Composable
fun PopularCardViewPreview() {
    AmmotelTheme {
        PopularCardView(
            modifier = Modifier.size(200.dp, 130.dp),
            name = "Turkiye",
            code = "TR",
            description = stringResource(id = strings.home_esim_popular_card_see_plan_title),
            icon = drawables.ic_data_transfer
        )
    }
}