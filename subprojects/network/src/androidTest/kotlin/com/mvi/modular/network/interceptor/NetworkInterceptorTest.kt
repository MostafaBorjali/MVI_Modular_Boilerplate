package com.mvi.modular.network.interceptor

import android.os.Build
import com.google.common.truth.Truth.assertThat
import com.mvi.modular.integration.domain.model.ApplicationInfo
import com.mvi.modular.integration.service.ApplicationInfoService
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


class NetworkInterceptorTest {

    @get:Rule
    val mockWebServer: MockWebServer = MockWebServer()

    private lateinit var networkInterceptor: NetworkInterceptor
    private lateinit var applicationInfoService: ApplicationInfoService

    private lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        applicationInfoService = Mockito.mock(ApplicationInfoService::class.java)
        networkInterceptor = NetworkInterceptor(applicationInfoService)
        okHttpClient = OkHttpClient().newBuilder().addInterceptor(networkInterceptor).build()
    }


    @Test
    fun test_every_request_must_have_application_info_header() {
        val applicationInfo = ApplicationInfo(
            applicationId = "test_application_id",
            versionCode = 1,
            versionName = "1.0",
            debug = true
        )
        Mockito.`when`(applicationInfoService.applicationInfo()).thenReturn(applicationInfo)

        mockWebServer.enqueue(MockResponse().setResponseCode(200))

        val request = Request.Builder().url(mockWebServer.url("/"))
            .get()
            .build()

        val response = okHttpClient.newCall(request).execute()

        assertThat(response.request.header("platform")).isNotNull()
        assertThat(response.request.header("os")).isNotNull()
        assertThat(response.request.header("os_version")).isNotNull()
        assertThat(response.request.header("app_version_code")).isNotNull()
        assertThat(response.request.header("app_version_name")).isNotNull()

        assertThat(response.request.header("platform")).isEqualTo("Android")
        assertThat(response.request.header("os")).isEqualTo("Android")
        assertThat(response.request.header("os_version")).isEqualTo(Build.VERSION.SDK_INT.toString())
        assertThat(response.request.header("app_version_code")).isEqualTo(applicationInfo.versionCode.toString())
        assertThat(response.request.header("app_version_name")).isEqualTo(applicationInfo.versionName)
    }
}