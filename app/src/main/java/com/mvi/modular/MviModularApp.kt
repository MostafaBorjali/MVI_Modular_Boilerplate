package com.mvi.modular


import com.mvi.modular.auth.di.authModule
import com.mvi.modular.crashlytics.lifecycle.CrashlyticsModuleLifecycleExtension
import com.mvi.modular.deeplink.di.deeplinkModule
import com.mvi.modular.di.appModule
import com.mvi.modular.error.di.errorModule
import com.mvi.modular.home.di.homeModule
import com.mvi.modular.integration.application.IntegrationApplication
import com.mvi.modular.integration.di.integrationModule
import com.mvi.modular.integration.domain.extension.LifecycleExtension
import com.mvi.modular.integration.domain.model.ApplicationInfo
import com.mvi.modular.integration.service.ApplicationInfoService
import com.mvi.modular.intro.di.introModule
import com.mvi.modular.lang.di.langModule
import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.service.LanguageConfigService
import com.mvi.modular.navigation.di.navigationModule
import com.mvi.modular.network.di.networkModule
import com.mvi.modular.notification.di.notificationModule
import com.mvi.modular.notification.lifecycle.NotificationModuleLifecycleExtension
import com.mvi.modular.persist.di.persistModule
import com.mvi.modular.user.di.userModule
import com.mvi.module.app.BuildConfig
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level


class MviModularApp : IntegrationApplication() {


    private val applicationInfoService: ApplicationInfoService by inject()
    private val languageConfigService: LanguageConfigService by inject()


    override fun onCreate() {
        /*
         * Start koin dependency injection platform at the first step, before call all super classes
         * inorder to inject whatever we want in parents classes
         */
        localStartKoin()

        super.onCreate()
        localConfigApplicationInfo()
        localConfigApplicationLanguage()
    }

    override fun lifecycleExtensions(): List<LifecycleExtension> {
        return listOf(

            NotificationModuleLifecycleExtension(),
            CrashlyticsModuleLifecycleExtension(),
        )
    }


    private fun localStartKoin() {
        startKoin {
            androidContext(this@MviModularApp)
            androidLogger(Level.DEBUG)

            modules(
                /*
                 * sub businesses
                 */
                introModule,
                homeModule,

                /*
                 * sub projects
                 */
                appModule,
                authModule,
                deeplinkModule,
                errorModule,
                integrationModule,
                langModule,
                navigationModule,
                networkModule(BuildConfig.TEST_MODE),
                notificationModule,
                persistModule(false),
                userModule,

                )
        }
    }

    private fun localConfigApplicationInfo() {
        val applicationInfo = ApplicationInfo(
            applicationId = BuildConfig.APPLICATION_ID,
            versionCode = BuildConfig.VERSION_CODE,
            versionName = BuildConfig.VERSION_NAME,
            debug = BuildConfig.TEST_MODE,
        )

        applicationInfoService.initialize(applicationInfo)
    }

    private fun localConfigApplicationLanguage() {
        val english = Lang(
            languageCode = "en",
            countryCode = "US",
            nativeTitle = "English",
            englishTitle = "English",
            direction = "ltr",
            isDefault = true,
            isEnable = true
        )
        val turkish = Lang(
            languageCode = "tr",
            countryCode = "TR",
            nativeTitle = "Türkçe",
            englishTitle = "Turkish",
            direction = "ltr",
            isDefault = false,
            isEnable = true
        )
        val spanish = Lang(
            languageCode = "es",
            countryCode = "ES",
            nativeTitle = "Español",
            englishTitle = "Spanish",
            direction = "ltr",
            isDefault = false,
            isEnable = true
        )

        languageConfigService.setApplicationLanguages(
            languages = arrayListOf(english, turkish, spanish)
        )
    }
}