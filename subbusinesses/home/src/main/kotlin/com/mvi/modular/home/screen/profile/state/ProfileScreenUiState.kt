package com.mvi.modular.home.screen.profile.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.strings.core.strings
import com.mvi.modular.ui.core.drawables


internal data class ProfileScreenUiState(
    val supportedLang: List<Lang> = emptyList(),
    val items: List<ProfileSettingItem> = emptyList(),
    val serverChanged: Boolean = false,

    )


internal sealed class ProfileSettingItem(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
) {

    data object ChangeServer : ProfileSettingItem(
        icon = drawables.ic_data_transfer,
        title = strings.home_profile_change_server,
    )
}