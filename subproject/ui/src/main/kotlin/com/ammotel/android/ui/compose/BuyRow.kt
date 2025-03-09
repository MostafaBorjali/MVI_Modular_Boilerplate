package com.ammotel.android.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ammotel.android.strings.core.strings
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalDimen
import com.ammotel.android.ui.theme.LocalPadding
import com.ammotel.android.ui.theme.LocalTypography


@Composable
fun BuyRowButton(
    modifier: Modifier,
    isLogin: Boolean,
    loading: Boolean,
    amount: String,
    onClick: () -> Unit,
) {
    Column(modifier = modifier) {
        HorizontalDivider()
        Spacer(modifier = Modifier.height(LocalPadding.current.small))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalPadding.current.horizontalOffset),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = amount,
                style = LocalTypography.current.ax2.headline,
            )

            Button(
                modifier = Modifier.widthIn(min = LocalDimen.current.minButtonWidth),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LocalColors.current.buttonColor
                ),
                shape = MaterialTheme.shapes.medium,
                onClick = onClick
            ) {
                if (isLogin) {
                    if (loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp),
                            color = Color.White,
                            strokeWidth = 2.dp,
                        )
                    } else {
                        Text(
                            text = stringResource(id = strings.general_buy_now),
                            style = LocalTypography.current.large.body,
                        )
                    }
                } else {
                    Text(
                        text = stringResource(id = strings.general_login),
                        style = LocalTypography.current.large.body
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(LocalPadding.current.small))
    }
}


@Preview(showBackground = true)
@Composable
internal fun BuyRowButtonPreview() {
    AmmotelTheme {
        BuyRowButton(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding(),
            isLogin = true,
            loading = false,
            amount = "$4156.5623",
            onClick = {},
        )
    }
}