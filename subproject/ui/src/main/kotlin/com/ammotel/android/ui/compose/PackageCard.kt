package com.ammotel.android.ui.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ammotel.android.ui.core.drawables
import com.ammotel.android.ui.extensions.toImageResource
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalDimen
import com.ammotel.android.ui.theme.LocalTypography
import com.ammotel.android.ui.theme.padding


@Composable
fun PackageCard(
    modifier: Modifier = Modifier,
    name: String,
    code: String,
    maxCounter: Int? = null,
    minCounter: Int? = null,
    defaultCounter: Int? = null,
    @DrawableRes icon: Int,
    remainingCredit: String,
    counterVisibility: Boolean = false,
    numberOfSim: (Int) -> Unit = { }
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = LocalColors.current.backgroundCardPrimary,
            contentColor = LocalColors.current.whiteLabelColorPrimary
        ),
        shape = MaterialTheme.shapes.large,
    ) {
        Box(
            modifier = Modifier.padding(top = MaterialTheme.padding.medium)
        ) {
            ESIMCardHeader(
                name = name,
                code = code,
                icon = icon,
                textStyle = LocalTypography.current.ax2.headline,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.padding.medium)
                    .align(Alignment.TopCenter),
            )

            Image(
                painter = painterResource(id = code.toImageResource()),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                alpha = 0.1f,
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .fillMaxHeight(),
            )

            Text(
                text = remainingCredit,
                style = LocalTypography.current.small.titleLarge,
                modifier = Modifier
                    .align(alignment = Alignment.BottomStart)
                    .padding(MaterialTheme.padding.medium)
            )
            if (counterVisibility) {
                CounterComponent(
                    modifier = modifier
                        .wrapContentSize(align = Alignment.BottomEnd)
                        .padding(
                            bottom = MaterialTheme.padding.medium,
                            //end = MaterialTheme.padding.medium,
                        ),
                    max = maxCounter ?: 10,
                    min = minCounter ?: 1,
                    defaultValue = defaultCounter ?: 1,
                ) { result ->
                    numberOfSim(result)
                }
            }
        }
    }
}

@Preview
@Composable
fun PackageCardPreview() {
    AmmotelTheme {
        PackageCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalDimen.current.packageCardHeight),
            name = "Thailand",
            code = "EG",
            remainingCredit = "1GB | 7Days",
            icon = drawables.ic_esim,
            counterVisibility = true,
        )
    }
}