package com.mvi.modular.lang.data.repository

import com.google.common.truth.Truth.assertThat
import com.mvi.modular.lang.core.LangConstants.KEY_CURRENT_LANG
import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.domain.repository.LanguageRepository
import com.mvi.modular.persist.service.PersistService
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class LanguageRepositoryTest {

    private lateinit var languageRepository: LanguageRepository
    private lateinit var persistService: PersistService

    @Before
    fun setUp() {
        persistService = mock(PersistService::class.java)
        languageRepository = DefaultLanguageRepository(persistService)
    }


    @Test
    fun `test set language list must set and hold languages`() {
        val languages = getDummyLangList()

        languageRepository.setLanguageList(languages)

        assertThat(languageRepository.getAllLanguages()).isNotEmpty()
        assertThat(languageRepository.getAllLanguages()).contains(languages[0])
        assertThat(languageRepository.getAllLanguages()).contains(languages[1])
    }

    @Test
    fun `test set current language must set on persist service`() {
        val lang = getDummyLangList()[0]

        languageRepository.setCurrentLanguage(lang)

        verify(persistService, times(1))
            .putString(KEY_CURRENT_LANG, lang.languageCode)
    }

    @Test
    fun `test get current language with existing language must return current language`() {
        val languages = getDummyLangList()

        languageRepository.setLanguageList(languages)

        `when`(persistService.getString(KEY_CURRENT_LANG)).thenReturn("en")

        val lang = languageRepository.getCurrentLanguage()
        assertThat(lang).isNotNull()
    }

    @Test
    fun `test get current language without setting current language must return null`() {
        val languages = getDummyLangList()

        languageRepository.setLanguageList(languages)

        `when`(persistService.getString(KEY_CURRENT_LANG)).thenReturn(null)

        val lang = languageRepository.getCurrentLanguage()
        assertThat(lang).isNull()
    }

    @Test
    fun `test get current language when persisted current language not exist in language list must return null`() {
        val languages = getDummyLangList()

        languageRepository.setLanguageList(languages)

        `when`(persistService.getString(KEY_CURRENT_LANG)).thenReturn("ir")

        val lang = languageRepository.getCurrentLanguage()
        assertThat(lang).isNull()
    }

    @Test
    fun `test get all languages must return all languages that already set`() {
        val languages = getDummyLangList()

        languageRepository.setLanguageList(languages)

        assertThat(languageRepository.getAllLanguages()).hasSize(2)
    }

    @Test
    fun `test get all languages must return empty list if not languages set before`() {
        assertThat(languageRepository.getAllLanguages()).hasSize(0)
    }

    private fun getDummyLangList() = arrayListOf(
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