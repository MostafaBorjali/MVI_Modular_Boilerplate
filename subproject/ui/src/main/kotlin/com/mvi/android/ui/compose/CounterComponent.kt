package com.mvi.android.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ammotel.android.ui.core.drawables
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalTypography
import com.ammotel.android.ui.theme.padding

@Composable
fun CounterComponent(
    modifier: Modifier,
    max: Int,
    min: Int,
    defaultValue: Int,
    eSimCount: (Int) -> Unit,
) {
    var count by remember { mutableIntStateOf(defaultValue) }

    Row(
        modifier = modifier.background(color = Color.Transparent),
    ) {
        Image(
            painter = painterResource(id = drawables.ic_minus),
            modifier = Modifier
                .padding(
                    top = MaterialTheme.padding.small,
                    bottom = MaterialTheme.padding.extraSmall
                )
                .clip(CircleShape)
                .clickable {
                    if (count > min) {
                        count--
                        eSimCount.invoke(count)
                    }
                },
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )

        Text(
            modifier = Modifier
                .defaultMinSize(40.dp)
                .padding(
                    top = MaterialTheme.padding.small,
                    bottom = MaterialTheme.padding.extraSmall
                ),
            text = "$count",
            textAlign = TextAlign.Center,
            style = LocalTypography.current.xxExtraLarge.headline,
            color = LocalColors.current.whiteLabelColorPrimary,
            overflow = TextOverflow.Ellipsis,
        )

        Image(
            painter = painterResource(id = drawables.ic_plus),
            modifier = Modifier
                .padding(
                    top = MaterialTheme.padding.small,
                    bottom = MaterialTheme.padding.extraSmall
                )
                .clip(CircleShape)
                .clickable {
                    if (count < max) {
                        count++
                        eSimCount.invoke(count)
                    }
                },
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CounterComponentPreview() {
    AmmotelTheme {
        CounterComponent(
            modifier = Modifier.wrapContentSize(),
            max = 10,
            min = 1,
            defaultValue = 1,
            eSimCount = {},
        )
    }
}
