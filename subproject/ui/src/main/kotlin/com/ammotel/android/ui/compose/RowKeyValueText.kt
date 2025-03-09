package com.ammotel.android.ui.compose

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ammotel.android.ui.core.drawables
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.LocalPadding
import com.ammotel.android.ui.theme.LocalTypography


@Composable
fun RowKeyValueText(
    modifier: Modifier,
    key: String,
    value: String,
    keyTextStyle: TextStyle? = null,
    valueTextStyle: TextStyle? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            modifier = Modifier.weight(1f),
            text = key,
            style = keyTextStyle ?: LocalTextStyle.current,
        )

        Text(
            modifier = Modifier,
            text = value,
            style = valueTextStyle ?: LocalTextStyle.current,
        )

        if (trailingIcon != null) {
            Spacer(modifier = Modifier.width(LocalPadding.current.extraSmall))
            trailingIcon()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RowKeyValueTextPreview() {
    AmmotelTheme {
        RowKeyValueText(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(16.dp),
            key = "Key",
            value = "Value",
            valueTextStyle = LocalTypography.current.large.headline,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = drawables.ic_arrow_right),
                    contentDescription = null
                )
            }
        )
    }
}