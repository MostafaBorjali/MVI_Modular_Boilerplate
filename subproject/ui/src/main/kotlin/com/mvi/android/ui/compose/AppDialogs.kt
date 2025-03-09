package com.mvi.android.ui.compose

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.ammotel.android.strings.core.strings
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalTypography


@Composable
fun Dialog(
    modifier: Modifier = Modifier,
    cancelable: Boolean = true,
    retryable: Boolean = false,
    title: String? = null,
    message: String,
    positiveText: String = if (retryable) {
        stringResource(id = strings.general_try_again)
    } else {
        stringResource(id = strings.general_confirm)
    },
    negativeText: String = stringResource(id = strings.general_dismiss),
    onDismissRequest: () -> Unit,
    onPositiveClicked: () -> Unit,
    onNegativeClicked: () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        shape = MaterialTheme.shapes.extraLarge,
        confirmButton = {
            TextButton(
                onClick = onPositiveClicked,
                shape = MaterialTheme.shapes.small,
            ) {
                Text(
                    text = positiveText,
                    style = LocalTypography.current.large.subhead,
                    color = LocalColors.current.blackLabelColorPrimary,
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onNegativeClicked,
                shape = MaterialTheme.shapes.small,
            ) {
                Text(
                    text = negativeText,
                    style = LocalTypography.current.large.subhead,
                    color = LocalColors.current.blackLabelColorPrimary,
                )
            }
        },
        title = {
            if (!title.isNullOrEmpty()) {
                Text(
                    text = title,
                    style = LocalTypography.current.large.title1,
                    color = LocalColors.current.blackLabelColorPrimary,
                )
            }
        },
        text = {
            Text(
                text = message,
                style = LocalTypography.current.extraLarge.body,
                color = LocalColors.current.blackLabelColorPrimary,
            )
        },
        properties = DialogProperties(
            dismissOnBackPress = cancelable,
            dismissOnClickOutside = cancelable,
        )
    )
}


@Preview(showBackground = true)
@Composable
fun DialogPreview() {
    AmmotelTheme {
        Dialog(
            title = "Warning",
            message = "This is a test message, are you sure is good?",
            onDismissRequest = {},
            onPositiveClicked = {},
            onNegativeClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DialogWithoutTitlePreview() {
    AmmotelTheme {
        Dialog(
            message = "This is a test message, are you sure is good?",
            onDismissRequest = {},
            onPositiveClicked = {},
            onNegativeClicked = {}
        )
    }
}