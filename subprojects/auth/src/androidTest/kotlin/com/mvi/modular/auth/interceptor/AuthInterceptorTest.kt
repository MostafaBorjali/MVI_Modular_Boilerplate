package com.mvi.modular.auth.interceptor

import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.auth.service.AuthService
import com.mvi.modular.error.domain.exception.AccessTokenExpiredException
import com.mvi.modular.error.domain.exception.AccessTokenNotFoundException
import com.mvi.modular.network.annotation.WithoutAuthentication
import com.google.common.truth.Truth.assertThat
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import retrofit2.Invocation
import retrofit2.Response
import retrofit2.http.GET

class AuthInterceptorTest {

    @get:Rule
    val mockWebServer: MockWebServer = MockWebServer()

    private lateinit var authInterceptor: AuthInterceptor
    private lateinit var authService: AuthService
    private lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        authService = mock(AuthService::class.java)
        authInterceptor = AuthInterceptor(authService)
        okHttpClient = OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
    }

    @Test
    fun test_if_request_has_without_authentication_annotation_should_not_check_tokens() {
        mockWebServer.enqueue(MockResponse().setResponseCode(200))

        val method = AuthApiTest::class.java.methods.toList()
            .first { it.name.equals("testWithoutAuth") }

        val request = Request.Builder().url(mockWebServer.url("/"))
            .get()
            .tag(
                Invocation::class.java,
                Invocation.of(method, mutableListOf("args"))
            )
            .build()

        okHttpClient.newCall(request).execute()

        verify(authService, times(0)).isAvailable(TokenType.AccessToken)
        verify(authService, times(0)).isValid(TokenType.AccessToken, true)
        verify(authService, times(0)).token(TokenType.AccessToken)
    }

    @Test
    fun test_if_request_has_not_without_authentication_annotation_and_access_token_not_available_must_throw_exception() {
        mockWebServer.enqueue(MockResponse().setResponseCode(200))
        `when`(authService.isAvailable(TokenType.AccessToken)).thenReturn(false)

        val method = AuthApiTest::class.java.methods.toList()
            .first { it.name.equals("testWithAuth") }

        val request = Request.Builder().url(mockWebServer.url("/"))
            .get()
            .tag(
                Invocation::class.java,
                Invocation.of(method, mutableListOf("args"))
            )
            .build()

        var exception: Exception? = null
        try {
            okHttpClient.newCall(request).execute()
        } catch (ex: Exception) {
            exception = ex
        }

        assertThat(exception).isNotNull()
        assertThat(exception).isInstanceOf(AccessTokenNotFoundException::class.java)
    }

    @Test
    fun test_if_request_has_not_without_authentication_annotation_and_access_token_not_valid_must_throw_exception() {
        mockWebServer.enqueue(MockResponse().setResponseCode(200))
        `when`(authService.isAvailable(TokenType.AccessToken)).thenReturn(true)
        `when`(authService.isValid(TokenType.AccessToken, true)).thenReturn(false)

        val method = AuthApiTest::class.java.methods.toList()
            .first { it.name.equals("testWithAuth") }

        val request = Request.Builder().url(mockWebServer.url("/"))
            .get()
            .tag(
                Invocation::class.java,
                Invocation.of(method, mutableListOf("args"))
            )
            .build()

        var exception: Exception? = null
        try {
            okHttpClient.newCall(request).execute()
        } catch (ex: Exception) {
            exception = ex
        }

        assertThat(exception).isNotNull()
        assertThat(exception).isInstanceOf(AccessTokenExpiredException::class.java)
    }

    @Test
    fun test_if_request_has_not_without_authentication_annotation_and_access_token_expired_must_throw_exception() {
        mockWebServer.enqueue(MockResponse().setResponseCode(401))
        `when`(authService.isAvailable(TokenType.AccessToken)).thenReturn(true)
        `when`(authService.isValid(TokenType.AccessToken, true)).thenReturn(true)
        `when`(authService.token(TokenType.AccessToken))
            .thenReturn("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")

        val method = AuthApiTest::class.java.methods.toList()
            .first { it.name.equals("testWithAuth") }

        val request = Request.Builder().url(mockWebServer.url("/"))
            .get()
            .tag(
                Invocation::class.java,
                Invocation.of(method, mutableListOf("args"))
            )
            .build()

        var exception: Exception? = null
        try {
            okHttpClient.newCall(request).execute()
        } catch (ex: Exception) {
            exception = ex
        }

        assertThat(exception).isNotNull()
        assertThat(exception).isInstanceOf(AccessTokenExpiredException::class.java)
    }

    @Test
    fun test_if_request_has_not_without_authentication_annotation_should_check_tokens() {
        mockWebServer.enqueue(MockResponse().setResponseCode(200))
        val token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
        `when`(authService.isAvailable(TokenType.AccessToken)).thenReturn(true)
        `when`(authService.isValid(TokenType.AccessToken, true)).thenReturn(true)
        `when`(authService.token(TokenType.AccessToken))
            .thenReturn(token)

        val method = AuthApiTest::class.java.methods.toList()
            .first { it.name.equals("testWithAuth") }

        val request = Request.Builder().url(mockWebServer.url("/"))
            .get()
            .tag(
                Invocation::class.java,
                Invocation.of(method, mutableListOf("args"))
            )
            .build()

        val response = okHttpClient.newCall(request).execute()

        assertThat(response.request.header("Authorization")).isNotNull()
        assertThat(response.request.header("Authorization")).isEqualTo("Bearer $token")
    }


    @Suppress("UNUSED")
    private interface AuthApiTest {

        @WithoutAuthentication
        @GET
        suspend fun testWithoutAuth(): Response<ResponseBody>

        @GET
        suspend fun testWithAuth(): Response<ResponseBody>
    }
}