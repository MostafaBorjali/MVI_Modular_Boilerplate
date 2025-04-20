package com.mvi.modular.lang.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.domain.model.Ltr
import com.mvi.modular.lang.domain.model.Rtl
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CheckCurrentLangDirectionUseCaseTest {

    private lateinit var checkCurrentLangDirectionUseCase: CheckCurrentLangDirectionUseCase
    private lateinit var getCurrentLangUseCase: GetCurrentLangUseCase

    @Before
    fun setUp() {
        getCurrentLangUseCase = Mockito.mock(GetCurrentLangUseCase::class.java)
        checkCurrentLangDirectionUseCase = DefaultCheckCurrentLangDirectionUseCase(
            getCurrentLangUseCase
        )
    }

    @Test
    fun `test check language direction for ltr language must return Ltr object`() {
        val lang = Lang(
            languageCode = "en",
            countryCode = "US",
            nativeTitle = "English",
            englishTitle = "English",
            direction = "ltr",
            isDefault = true
        )

        Mockito.`when`(getCurrentLangUseCase.invoke()).thenReturn(lang)

        assertThat(checkCurrentLangDirectionUseCase(Ltr)).isTrue()
        assertThat(checkCurrentLangDirectionUseCase(Rtl)).isFalse()
    }

    @Test
    fun `test check language direction for rtl language must return Rtl object`() {
        val lang = Lang(
            languageCode = "en",
            countryCode = "US",
            nativeTitle = "English",
            englishTitle = "English",
            direction = "rtl",
            isDefault = true
        )

        Mockito.`when`(getCurrentLangUseCase.invoke()).thenReturn(lang)

        assertThat(checkCurrentLangDirectionUseCase(Ltr)).isFalse()
        assertThat(checkCurrentLangDirectionUseCase(Rtl)).isTrue()
    }
}