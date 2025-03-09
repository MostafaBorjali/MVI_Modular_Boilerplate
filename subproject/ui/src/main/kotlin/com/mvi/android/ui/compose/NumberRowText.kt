package com.mvi.android.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalPadding
import com.ammotel.android.ui.theme.LocalTypography


@Composable
fun NumberTextRow(
    modifier: Modifier = Modifier,
    number: String,
    text: String,
    dark: Boolean,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .size(22.dp)
                .clip(CircleShape)
                .background(
                    if (dark) {
                        LocalColors.current.whiteLabelColorPrimary
                    } else {
                        LocalColors.current.blackLabelColorPrimary
                    }
                ),
            text = number,
            fontSize = 14.sp,
            color = if (dark) {
                LocalColors.current.blackLabelColorPrimary
            } else {
                LocalColors.current.whiteLabelColorPrimary
            },
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.width(LocalPadding.current.extraSmall))

        Text(
            text = text,
            style = LocalTypography.current.medium.body,
            color = if (dark) {
                LocalColors.current.whiteLabelColorPrimary
            } else {
                LocalColors.current.blackLabelColorPrimary
            },
        )
    }
}

@Preview
@Composable
fun NumberTextRowDarkPreview() {
    AmmotelTheme {
        NumberTextRow(
            number = "1",
            text = "Hello world!",
            dark = true,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NumberTextRowLightPreview() {
    AmmotelTheme {
        NumberTextRow(
            number = "1",
            text = "Hello world!",
            dark = false,
        )
    }
}