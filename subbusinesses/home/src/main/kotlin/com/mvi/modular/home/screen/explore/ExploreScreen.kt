package com.mvi.modular.home.screen.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvi.modular.strings.core.strings
import com.mvi.modular.ui.theme.LocalColors
import com.mvi.modular.ui.theme.LocalTypography
import com.mvi.modular.ui.theme.MviModularTheme
import com.mvi.modular.ui.theme.padding


@Composable
internal fun ExploreScreen() {

    ExploreContent(Modifier)
}

@Composable
private fun ExploreContent(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LocalColors.current.surfaceColor)
    ) {

        Text(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.padding.small,
                    start = MaterialTheme.padding.extraLarge,
                    end = MaterialTheme.padding.extraLarge
                )
                .align(Alignment.Center),
            text = stringResource(id = strings.home_explore_title),
            textAlign = TextAlign.Start,
            style = LocalTypography.current.large.body,
            color = LocalColors.current.blackLabelColorPrimary,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview() {
    MviModularTheme {
        ExploreContent(Modifier)
    }
}