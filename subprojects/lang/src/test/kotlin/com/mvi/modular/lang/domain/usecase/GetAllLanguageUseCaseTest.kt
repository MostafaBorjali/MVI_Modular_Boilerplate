package com.mvi.modular.lang.domain.usecase

import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.domain.repository.LanguageRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class GetAllLanguageUseCaseTest {

    private lateinit var getAllLanguageUseCase: GetAllLanguageUseCase
    private lateinit var languageRepository: LanguageRepository

    @Before
    fun setUp() {
        languageRepository = mock(LanguageRepository::class.java)
        getAllLanguageUseCase = DefaultGetAllLanguageUseCase(languageRepository)
    }


    @Test
    fun `test get all language before set languages must throw exception`() {
        Mockito.`when`(languageRepository.getAllLanguages()).thenReturn(emptyList())

        var isExceptionThrow = false

        try {
            getAllLanguageUseCase()
        } catch (ex: IllegalStateException) {
            isExceptionThrow = true
        }

        assertThat(isExceptionThrow).isTrue()
    }

    @Test
    fun `test get all language after set languages must returns all languages`() {
        Mockito.`when`(languageRepository.getAllLanguages()).thenReturn(
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

        assertThat(getAllLanguageUseCase()).hasSize(2)
    }
}