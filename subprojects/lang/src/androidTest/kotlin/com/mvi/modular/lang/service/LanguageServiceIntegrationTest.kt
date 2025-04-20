package com.mvi.modular.lang.service

import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.mvi.modular.lang.di.langModule
import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.persist.di.persistModule
import com.mvi.modular.persist.service.PersistService
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

@MediumTest
class LanguageServiceIntegrationTest : KoinTest {

    @get:Rule(order = 0)
    val koinTestRule = KoinTestRule.create {
        androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
        modules(langModule, persistModule(true))
    }

    private val persistService: PersistService by inject()
    private val languageConfigService: LanguageConfigService by inject()
    private val languageService: LanguageService by inject()


    @Before
    fun setUp() {
        languageConfigService.setApplicationLanguages(getDummyLang())
    }

    @After
    fun tearDown() {
        persistService.removeAll()
    }

    @Test
    fun test_check_language_selected_must_return_true_if_any_language_has_set_before() {
        val lang = languageService.getAllLanguages()[0]
        languageService.setCurrentLanguage(lang)
        assertThat(languageService.isLanguageSelected()).isTrue()
    }

    @Test
    fun test_check_language_selected_must_return_false_if_any_language_has_not_set_before() {
        assertThat(languageService.isLanguageSelected()).isFalse()
    }

    @Test
    fun test_get_all_languages_must_returns_list_of_application_lang() {
        val languages = languageService.getAllLanguages()
        assertThat(languages).hasSize(2)
    }

    @Test
    fun test_get_current_lang_before_set_any_one_must_returns_default_lang_in_list() {
        val currentLang = languageService.getCurrentLanguage()
        assertThat(currentLang.languageCode).isEqualTo("en")
        assertThat(currentLang.isDefault).isTrue()
    }

    @Test
    fun test_get_current_lang_after_set_lang_must_returns_exact_lang() {
        val lang = Lang(
            languageCode = "tr",
            countryCode = "TR",
            nativeTitle = "Turkish",
            englishTitle = "Turkish",
            direction = "ltr",
            isDefault = false
        )
        languageService.setCurrentLanguage(lang)
        val currentLang = languageService.getCurrentLanguage()

        assertThat(currentLang).isEqualTo(lang)
    }


    private fun getDummyLang() = arrayListOf(
        Lang(
            languageCode = "en",
            countryCode = "US",
            nativeTitle = "English",
            englishTitle = "English",
            direction = "ltr",
            isDefault = true
        ),
        Lang(
            languageCode = "tr",
            countryCode = "TR",
            nativeTitle = "Turkish",
            englishTitle = "Turkish",
            direction = "ltr",
            isDefault = false
        )
    )
}