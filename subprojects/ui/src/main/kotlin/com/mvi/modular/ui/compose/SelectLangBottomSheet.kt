package com.mvi.modular.ui.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.strings.core.strings
import com.mvi.modular.ui.core.drawables
import com.mvi.modular.ui.theme.DefaultRoundCornerShape
import com.mvi.modular.ui.theme.LocalColors
import com.mvi.modular.ui.theme.LocalPadding
import com.mvi.modular.ui.theme.LocalTypography
import com.mvi.modular.ui.theme.dimen
import com.mvi.modular.ui.theme.padding
import com.mvi.modular.utils.string.toFlagEmoji


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectLanguage(
    modifier: Modifier = Modifier,
    languages: List<Lang>,
    onSelected: (Lang?) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    var selectedLang by remember {
        mutableStateOf<Lang?>(null)
    }

    LaunchedEffect(key1 = selectedLang) {
        if (selectedLang != null) {
            sheetState.hide()
            onSelected(selectedLang)
        }
    }

    ModalBottomSheet(
        modifier = modifier.heightIn(min = MaterialTheme.dimen.bottomSheetMinHeight),
        onDismissRequest = { onSelected(selectedLang) },
        sheetState = sheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        LanguageList(
            modifier = Modifier.fillMaxWidth(),
            languages = languages,
        ) { lang ->
            selectedLang = lang
        }
    }
}


@Composable
fun LanguageList(
    modifier: Modifier,
    languages: List<Lang>,
    onClick: (Lang) -> Unit,
) {
    Column(
        modifier = modifier.padding(horizontal = MaterialTheme.padding.horizontalOffset)
    ) {
        Text(
            text = stringResource(id = strings.select_lang_text_title),
            style = LocalTypography.current.large.title1,
            color = LocalColors.current.blackLabelColorPrimary,
        )

        Text(
            text = stringResource(id = strings.select_lang_text_description),
            style = LocalTypography.current.large.body,
            color = LocalColors.current.whiteLabelColorPrimary,
        )

        Spacer(modifier = Modifier.height(LocalPadding.current.large))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(LocalPadding.current.small),
        ) {
            items(languages) { lang ->
                LanguageItem(
                    modifier = Modifier.fillMaxWidth(),
                    lang = lang,
                    onClick = onClick,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LanguageListPreview() {
    val lang = Lang(
        languageCode = "en",
        countryCode = "US",
        nativeTitle = "English",
        englishTitle = "English",
        direction = "ltr",
        isDefault = true,
        isEnable = true
    )
    LanguageList(
        modifier = Modifier.fillMaxWidth(),
        languages = listOf(lang, lang, lang),
        onClick = {},
    )
}

@Composable
fun LanguageItem(
    modifier: Modifier,
    lang: Lang,
    onClick: (Lang) -> Unit,
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .border(
                width = MaterialTheme.dimen.borderWidth,
                color = LocalColors.current.primaryColor,
                shape = DefaultRoundCornerShape
            )
            .clip(DefaultRoundCornerShape)
            .clickable { onClick(lang) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.padding(
                start = MaterialTheme.padding.medium,
                top = MaterialTheme.padding.medium,
                bottom = MaterialTheme.padding.medium,
            ),
            text = lang.countryCode.toFlagEmoji(),
            fontSize = 20.sp,
        )

        Text(
            modifier = Modifier
                .padding(start = MaterialTheme.padding.small)
                .weight(1.0f),
            text = lang.englishTitle,
            style = LocalTypography.current.extraSmall.body,
            color = LocalColors.current.blackLabelColorPrimary,
        )

        Icon(
            modifier = Modifier.padding(end = MaterialTheme.padding.small),
            painter = painterResource(id = drawables.ic_arrow_right),
            contentDescription = "SelectArrow",
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 100)
@Composable
fun LanguageItemPreview() {
    val lang = Lang(
        languageCode = "en",
        countryCode = "US",
        nativeTitle = "English",
        englishTitle = "English",
        direction = "ltr",
        isDefault = true,
        isEnable = true
    )
    LanguageItem(
        modifier = Modifier.fillMaxWidth(),
        lang = lang,
        onClick = {},
    )
}