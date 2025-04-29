package com.mvi.modular.home.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.modular.error.service.ErrorService
import com.mvi.modular.home.screen.profile.event.ProfileScreenEvent
import com.mvi.modular.home.screen.profile.state.ProfileScreenUiState
import com.mvi.modular.home.screen.profile.state.ProfileSettingItem
import com.mvi.modular.home.screen.profile.state.ProfileSettingItem.ChangeServer
import com.mvi.modular.integration.service.ApplicationInfoService
import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.service.LanguageService
import com.mvi.modular.navigation.service.NavigationService
import com.mvi.modular.notification.service.NotificationService
import com.mvi.modular.persist.core.PersistConstants.KEY_SERVER_ENV
import com.mvi.modular.persist.service.PersistService
import com.mvi.modular.user.service.UserProfileService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


internal class ProfileScreenViewModel(
    private val userProfileService: UserProfileService,
    private val applicationInfoService: ApplicationInfoService,
    private val navigationService: NavigationService,
    private val notificationService: NotificationService,
    private val errorService: ErrorService,
    private val languageService: LanguageService,
    private val persistService: PersistService,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileScreenUiState())
    val uiState = _state.asStateFlow()

    fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            is ProfileScreenEvent.Load -> {
                fetchProfileDefaultConfigs()
            }
            is ProfileScreenEvent.ChangeLanguage -> {
                changeLanguage(event.lang)
            }
            is ProfileScreenEvent.ProfileSettingItemClicked -> {
                when (event.item) {

                    ChangeServer -> {
                        viewModelScope.launch(dispatcher) {
                             changeServer()
                        }

                    }
                    else -> { }
                }
            }
        }
    }
    private fun changeLanguage(lang: Lang) {
        viewModelScope.launch(dispatcher) {
            languageService.setCurrentLanguage(lang)
            _state.update { currentState ->
                currentState.copy(
                    language = lang.languageCode
                )
            }
            if (userProfileService.isLogin()) {
                userProfileService.updateUserProfile(token = null, lang = lang.languageCode)
            }
        }
    }

    private fun changeServer() {
        viewModelScope.launch(dispatcher) {
            userProfileService.logout()
            notificationService.removeToken()
            val env = persistService.getBoolean(KEY_SERVER_ENV) ?: false
            persistService.putBoolean(KEY_SERVER_ENV, !env)
            _state.update { currentState ->
                currentState.copy(serverChanged = true)
            }
        }
    }
    private fun fetchProfileDefaultConfigs() {
        viewModelScope.launch(dispatcher) {
            val profile = userProfileService.getUser()
            val lang = languageService.getCurrentLanguage()
            val supportedLang = languageService.getAllLanguages()
            val server = if (applicationInfoService.applicationInfo()?.debug == true) {
                if (!persistService.contains(KEY_SERVER_ENV)) {
                    persistService.putBoolean(KEY_SERVER_ENV, false)
                }
                val production = persistService.getBoolean(KEY_SERVER_ENV) ?: false
                if (production) "prod" else "dev"
            } else {
                ""
            }
            _state.value = ProfileScreenUiState(
                isLogin = profile != null,
                email = profile?.email,
                language = lang.languageCode,
                server = server,
                supportedLang = supportedLang,
                items = getProfileSettingItems(profile != null),
            )
        }
    }
    private fun getProfileSettingItems(login: Boolean): List<ProfileSettingItem> {
        val list: MutableList<ProfileSettingItem> = mutableListOf(
            ProfileSettingItem.ChangeLanguage
        )

        if (applicationInfoService.applicationInfo()?.debug == true) {
            list.add(ChangeServer)
        }
        return list
    }

}