package com.mvi.modular.lang.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.mvi.modular.lang.domain.manager.DeviceLanguageManager
import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.domain.repository.LanguageRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetCurrentLangUseCaseTest {

    private lateinit var getCurrentLangUseCase: GetCurrentLangUseCase
    private lateinit var getAllLanguageUseCase: GetAllLanguageUseCase
    private lateinit var languageRepository: LanguageRepository
    private lateinit var deviceLanguageManager: DeviceLanguageManager

    @Before
    fun setUp() {
        getAllLanguageUseCase = mock(GetAllLanguageUseCase::class.java)
        languageRepository = mock(LanguageRepository::class.java)
        deviceLanguageManager = mock(DeviceLanguageManager::class.java)
        getCurrentLangUseCase = DefaultGetCurrentLangUseCase(
            getAllLanguageUseCase,
            languageRepository,
            deviceLanguageManager
        )
    }


    @Test
    fun `test get current lang must returns current lang if exist`() {
        val lang = Lang(
            languageCode = "en",
            countryCode = "US",
            nativeTitle = "English",
            englishTitle = "English",
            direction = "ltr",
            isDefault = true
        )
        `when`(languageRepository.getCurrentLanguage()).thenReturn(lang)

        val currentLang = getCurrentLangUseCase()
        assertThat(currentLang).isSameInstanceAs(lang)
    }

    @Test
    fun `test get current lang must returns default lang if current lang not exist`() {
        `when`(languageRepository.getCurrentLanguage()).thenReturn(null)
        `when`(getAllLanguageUseCase.invoke()).thenReturn(
            arrayListOf(
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
                    nativeTitle = "Türkçe",
                    englishTitle = "Turkish",
                    direction = "ltr",
                    isDefault = false
                )
            )
        )

        val currentLang = getCurrentLangUseCase()
        assertThat(currentLang.isDefault).isTrue()
        assertThat(currentLang.languageCode).isEqualTo("en")
    }

    @Test
    fun `test get current lang must returns system current lang if exist in language list and default lang not set`() {
        `when`(languageRepository.getCurrentLanguage()).thenReturn(null)
        `when`(getAllLanguageUseCase.invoke()).thenReturn(
            arrayListOf(
                Lang(
                    languageCode = "en",
                    countryCode = "US",
                    nativeTitle = "English",
                    englishTitle = "English",
                    direction = "ltr",
                    isDefault = false
                ),
                Lang(
                    languageCode = "tr",
                    countryCode = "TR",
                    nativeTitle = "Türkçe",
                    englishTitle = "Turkish",
                    direction = "ltr",
                    isDefault = false
                )
            )
        )

        `when`(deviceLanguageManager.getDeviceCurrentLang()).thenReturn("en")

        val currentLang = getCurrentLangUseCase()
        assertThat(currentLang.languageCode).isEqualTo("en")
    }

    @Test
    fun `test get current lang must throw exception if current lang and default lang and system lang not exist`() {
        `when`(languageRepository.getCurrentLanguage()).thenReturn(null)
        `when`(getAllLanguageUseCase.invoke()).thenReturn(
            arrayListOf(
                Lang(
                    languageCode = "en",
                    countryCode = "US",
                    nativeTitle = "English",
                    englishTitle = "English",
                    direction = "ltr",
                    isDefault = false
                ),
                Lang(
                    languageCode = "tr",
                    countryCode = "TR",
                    nativeTitle = "Türkçe",
                    englishTitle = "Turkish",
                    direction = "ltr",
                    isDefault = false
                )
            )
        )

        `when`(deviceLanguageManager.getDeviceCurrentLang()).thenReturn("ir")

        var exception: Exception? = null
        try {
            getCurrentLangUseCase()
        } catch (ex: IllegalStateException) {
            exception = ex
        }

        assertThat(exception).isNotNull()
        assertThat(exception).isInstanceOf(IllegalStateException::class.java)
    }
}