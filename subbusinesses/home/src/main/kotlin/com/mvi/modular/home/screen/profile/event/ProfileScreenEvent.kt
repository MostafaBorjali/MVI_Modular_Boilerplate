package com.mvi.modular.home.screen.profile.event

import com.mvi.modular.home.screen.profile.state.ProfileSettingItem
import com.mvi.modular.lang.domain.model.Lang


internal sealed interface ProfileScreenEvent {
    /**
     *
     */
    data object Load : ProfileScreenEvent

    /**
     *
     */
    data class ChangeLanguage(val lang: Lang) : ProfileScreenEvent

    /**
     *
     */
    data class ProfileSettingItemClicked(val item: ProfileSettingItem) : ProfileScreenEvent
}
