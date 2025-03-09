package com.mvi.android.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ammotel.android.strings.core.strings
import com.ammotel.android.ui.core.drawables
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.DefaultRoundCornerShape
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalTypography
import com.ammotel.android.ui.theme.padding


@Composable
fun EmptyState(
    modifier: Modifier = Modifier,
    isActiveTab: Boolean,
    shape: Shape = RectangleShape,
    color: Color = MaterialTheme.colorScheme.surface,
    onClick: (() -> Unit)? = null,
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = color,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(vertical = MaterialTheme.padding.extraLarge),
                painter =
                painterResource(id = if (isActiveTab) drawables.bg_empty_active else drawables.bg_empty_expired),
                contentDescription = null
            )
            Text(
                modifier = Modifier,
                style = LocalTypography.current.extraLarge.body,
                color = LocalColors.current.labelColorTertiary,
                text = stringResource(id = if (isActiveTab) strings.my_esim_empty_active else strings.my_esim_empty_expired)
            )

            if (onClick != null) {
                Button(
                    modifier = Modifier
                        .padding(
                            vertical = MaterialTheme.padding.large,
                            horizontal = MaterialTheme.padding.extraLarge
                        )
                        .background(
                            shape = DefaultRoundCornerShape,
                            color = LocalColors.current.secondaryColor
                        )
                        .fillMaxWidth(),
                    colors = ButtonColors(
                        containerColor = LocalColors.current.secondaryColor,
                        contentColor = LocalColors.current.whiteLabelColorPrimary,
                        disabledContainerColor = LocalColors.current.backgroundBezeledButtonPrimaryColor,
                        disabledContentColor = LocalColors.current.labelColorSecondary
                    ),

                    onClick = onClick
                ) {
                    Text(text = stringResource(id = strings.general_buy_new_esim))
                }
            }
        }
    }
}

@Preview(widthDp = 340, heightDp = 450)
@Composable
fun EmptyStatePreview() {
    AmmotelTheme {
        EmptyState(
            isActiveTab = true,

            )
    }
}

@Preview(widthDp = 340, heightDp = 450)
@Composable
fun EmptyStateWithoutButtonPreview() {
    AmmotelTheme {
        EmptyState(
            isActiveTab = true,
        )
    }
}

