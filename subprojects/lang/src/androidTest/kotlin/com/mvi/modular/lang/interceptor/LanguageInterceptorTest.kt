package com.mvi.modular.lang.interceptor

import com.mvi.modular.lang.domain.model.Lang
import com.mvi.modular.lang.service.LanguageService
import com.google.common.truth.Truth.assertThat
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class LanguageInterceptorTest {

    @get:Rule
    val mockWebServer: MockWebServer = MockWebServer()

    private lateinit var languageInterceptor: LanguageInterceptor
    private lateinit var languageService: LanguageService

    private lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        languageService = mock(LanguageService::class.java)
        languageInterceptor = LanguageInterceptor(languageService)
        okHttpClient = OkHttpClient().newBuilder().addInterceptor(languageInterceptor).build()
    }

    @Test
    fun test_every_request_must_have_accept_language_header() {
        val lang = Lang(
            languageCode = "en",
            countryCode = "US",
            nativeTitle = "English",
            englishTitle = "English",
            direction = "ltr",
            isDefault = true,
            isEnable = true
        )
        `when`(languageService.getCurrentLanguage()).thenReturn(lang)

        mockWebServer.enqueue(MockResponse().setResponseCode(200))

        val request = Request.Builder().url(mockWebServer.url("/"))
            .get()
            .build()

        val response = okHttpClient.newCall(request).execute()

        assertThat(response.request.header("lang")).isNotNull()
        assertThat(response.request.header("lang")).isEqualTo(lang.languageCode)
    }
}