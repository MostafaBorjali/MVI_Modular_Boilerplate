package com.ammotel.android.ui.compose.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ammotel.android.strings.core.strings
import com.ammotel.android.ui.core.drawables
import com.ammotel.android.ui.theme.AmmotelTheme
import com.ammotel.android.ui.theme.DefaultRoundCornerShape
import com.ammotel.android.ui.theme.LocalColors
import com.ammotel.android.ui.theme.LocalPadding
import com.ammotel.android.ui.theme.LocalTypography
import com.ammotel.android.ui.theme.padding


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateNameBottomSheetView(
    defaultNickname: String,
    isGroup: Boolean = false,
    nameEntered: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        modifier = Modifier.wrapContentHeight(),
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        properties = ModalBottomSheetDefaults.properties(),
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        UpdateNameContentView(
            modifier = Modifier.wrapContentSize(),
            defaultNickname = defaultNickname,
            isGroup = isGroup,
            keyboardController = keyboardController,
            nameEntered = nameEntered
        )

    }

}

@Composable
private fun UpdateNameContentView(
    modifier: Modifier,
    defaultNickname: String,
    isGroup: Boolean,
    keyboardController: SoftwareKeyboardController?,
    nameEntered: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    var input by rememberSaveable { mutableStateOf(defaultNickname) }
    val onValueChanged: (String) -> Unit = { value ->
        input = value
    }
    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.padding.small,
                    start = MaterialTheme.padding.extraLarge,
                    end = MaterialTheme.padding.extraLarge
                ),
            text = if (isGroup) {
                stringResource(id = strings.group_edit_bottom_sheet_title)
            } else {
                stringResource(id = strings.my_esim_share_bottom_sheet_title)
            },
            textAlign = TextAlign.Center,
            style = LocalTypography.current.small.title3,
            color = LocalColors.current.blackLabelColorPrimary,
        )
        Spacer(modifier = Modifier.height(LocalPadding.current.extraLarge))
        Spacer(modifier = Modifier.height(LocalPadding.current.extraLarge))

        OutlinedTextField(
            value = input,
            onValueChange = onValueChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = MaterialTheme.padding.small,
                    end = MaterialTheme.padding.extraLarge,
                    start = MaterialTheme.padding.extraLarge
                )
                .focusRequester(focusRequester),
            enabled = true,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (input.isNotEmpty()) {
                        nameEntered(input)
                        keyboardController?.hide()
                    }
                }
            ),
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            placeholder = {
                Text(
                    text = stringResource(id = strings.general_nick_name),
                    style = LocalTypography.current.large.body,
                    color = LocalColors.current.labelColorQuaternary,
                )
            },
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = LocalColors.current.textFieldBorderColorActive,
                unfocusedIndicatorColor = LocalColors.current.textFieldBorderColorDefault,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = LocalColors.current.textFieldBackgroundColor,
                unfocusedContainerColor = LocalColors.current.textFieldBackgroundColor,
                cursorColor = LocalColors.current.indicatorColor,
            ),
            trailingIcon = {
                if (input.isNotEmpty()) {
                    Row {
                        IconButton(onClick = { onValueChanged("") }) {
                            Icon(
                                painter = painterResource(id = drawables.ic_clear),
                                "ClearEmail",
                            )
                        }
                    }
                }
            },
            shape = DefaultRoundCornerShape
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(
                    top = MaterialTheme.padding.large,
                    end = MaterialTheme.padding.extraLarge,
                    start = MaterialTheme.padding.extraLarge
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = LocalColors.current.buttonColor,
            ),
            enabled = input.isNotEmpty(),
            shape = DefaultRoundCornerShape,
            onClick = {
                nameEntered(input)
                keyboardController?.hide()
            },
        ) {
            Text(
                modifier = Modifier.padding(vertical = LocalPadding.current.extraSmall),
                text = stringResource(id = strings.general_save),
                style = LocalTypography.current.large.body,
                color = if (input.isNotEmpty()) {
                    LocalColors.current.whiteLabelColorPrimary
                } else {
                    LocalColors.current.lineColor
                },
            )
        }
        Spacer(modifier = Modifier.height(LocalPadding.current.extraLarge))

    }
}

@Preview
@Composable
private fun UpdateNameContentViewPreview() {
    AmmotelTheme {
        UpdateNameContentView(
            Modifier
                .wrapContentSize()
                .background(Color.White),
            isGroup = true,
            keyboardController = null,
            defaultNickname = "Turkiye Tour - 2024/06/05",
            nameEntered = { },

            )
    }
}
