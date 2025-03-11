package com.mvi.modular.ui.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mvi.modular.ui.theme.LocalColors
import com.mvi.modular.ui.theme.LocalTypography
import com.mvi.modular.ui.theme.MviModularTheme


@Composable
fun ToolBar(
    modifier: Modifier,
    title: String,
    showTitle: Boolean = true,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart,
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
            )
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = showTitle,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = title,
                style = LocalTypography.current.extraSmall.title1,
                color = LocalColors.current.blackLabelColorPrimary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToolBarPreview() {
    MviModularTheme {
        ToolBar(
            modifier = Modifier.fillMaxWidth(),
            title = "My ToolBar",
            onBackClick = {},
        )
    }
}