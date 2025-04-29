package com.mvi.modular.home.screen.profile.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mvi.modular.error.domain.model.ErrorEvent
import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.strings.core.strings
import com.mvi.modular.ui.core.drawables


internal data class ProfileScreenUiState(
    val isLogin: Boolean = false,
    val email: String? = null,
    val language: String = "",
    val server: String = "",
    val supportedLang: List<Lang> = emptyList(),
    val items: List<ProfileSettingItem> = emptyList(),
    val serverChanged: Boolean = false,
    val errorInfo: ErrorEvent? = null,
    )


internal sealed class ProfileSettingItem(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
) {

    data object ChangeServer : ProfileSettingItem(
        icon = drawables.ic_data_transfer,
        title = strings.home_profile_change_server,
    )
    data object ChangeLanguage : ProfileSettingItem(
        icon = drawables.ic_localization,
        title = strings.home_profile_change_server,
    )
}