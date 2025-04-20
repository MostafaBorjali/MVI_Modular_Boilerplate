package com.mvi.modular.home.screen.profile.event

import com.mvi.modular.home.screen.profile.state.ProfileSettingItem


internal sealed interface ProfileScreenEvent {

    data class ProfileSettingItemClicked(val item: ProfileSettingItem) : ProfileScreenEvent
}