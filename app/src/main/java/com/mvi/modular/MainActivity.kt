package com.mvi.modular

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.mvi.modular.lang.service.LanguageService
import com.mvi.modular.lang.service.LocalizedContextService
import com.mvi.modular.navigation.MviModularNavigation
import com.mvi.modular.navigation.domain.model.Destination
import com.mvi.modular.navigation.service.NavigationService
import com.mvi.modular.persist.service.PersistService
import com.mvi.modular.ui.theme.LocalColors
import com.mvi.modular.ui.theme.MviModularTheme
import org.koin.android.ext.android.inject
import org.koin.compose.KoinContext


class MainActivity : ComponentActivity() {
    private val navigationService: NavigationService by inject()
    private val localizedContextService: LocalizedContextService by inject()
    private val languageService: LanguageService by inject()
    private val persistService: PersistService by inject()

    override fun attachBaseContext(newBase: Context?) {
        val localizedContext = newBase?.let {
            localizedContextService.localizedContext(it)
        }
        super.attachBaseContext(localizedContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val direction = if (languageService.isCurrentLanguageLtr()) {
                LayoutDirection.Ltr
            } else {
                LayoutDirection.Rtl
            }
            KoinContext {
                MviModularTheme() {
                    CompositionLocalProvider(LocalLayoutDirection provides direction) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = LocalColors.current.surfaceColor,
                        ) {
                            MviModularNavigation(
                                modifier = Modifier.fillMaxSize(),
                                navigationService = navigationService,
                                persistService = persistService,
                                startDestination = Destination.Splash
                            )
                        }
                    }
                }
            }
        }

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }
}