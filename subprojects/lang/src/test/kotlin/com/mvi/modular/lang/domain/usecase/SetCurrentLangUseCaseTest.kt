package com.mvi.modular.lang.domain.usecase

import com.google.common.truth.Truth
import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.domain.repository.LanguageRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class SetCurrentLangUseCaseTest {

    private lateinit var setCurrentLangUseCase: SetCurrentLangUseCase
    private lateinit var getAllLanguageUseCase: GetAllLanguageUseCase
    private lateinit var languageRepository: LanguageRepository


    @Before
    fun setUp() {
        languageRepository = mock(LanguageRepository::class.java)
        getAllLanguageUseCase = mock(GetAllLanguageUseCase::class.java)
        setCurrentLangUseCase = DefaultSetCurrentLangUseCase(
            getAllLanguageUseCase,
            languageRepository
        )
    }


    @Test
    fun `test set lang that exists in language list must set as current lang then notify all extensions`() {
        val languages = getDummyLang()
        val lang = languages[0]
        `when`(getAllLanguageUseCase.invoke()).thenReturn(languages)

        setCurrentLangUseCase(lang)

        verify(languageRepository, times(1)).setCurrentLanguage(lang)
    }

    @Test
    fun `test set lang that not exists in language list must throw an exception`() {
        val languages = getDummyLang()
        val lang = Lang(
            languageCode = "fa",
            countryCode = "IR",
            nativeTitle = "فارسی",
            englishTitle = "Persian",
            direction = "rtl",
            isDefault = false
        )
        `when`(getAllLanguageUseCase.invoke()).thenReturn(languages)

        var exception: Exception? = null
        try {
            setCurrentLangUseCase(lang)
        } catch (ex: IllegalStateException) {
            exception = ex
        }

        Truth.assertThat(exception).isNotNull()
        Truth.assertThat(exception).isInstanceOf(IllegalStateException::class.java)
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