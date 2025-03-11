package com.mvi.modular.auth.data.datasource

import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.mvi.modular.auth.domain.model.AuthToken
import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.persist.di.persistModule
import com.mvi.modular.persist.service.PersistService
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject


@MediumTest
class ReadWriteTokenDataSourceIntegrationTest : KoinTest {

    @get:Rule(order = 0)
    val koinTestRule = KoinTestRule.create {
        androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
        modules(persistModule(true))
    }

    private val persistService: PersistService by inject()
    private lateinit var readWriteTokenDataSource: ReadWriteTokenDataSource


    @Before
    fun setUp() {
        readWriteTokenDataSource = DefaultReadWriteTokenDataSource(persistService)
    }

    @After
    fun tearDown() {
        persistService.removeAll()
    }

    @Test
    fun test_write_token_successfully() {
        val authToken = generateTestAuthToken()
        val result = readWriteTokenDataSource.write(authToken, TokenType.AccessToken)
        assertThat(result).isTrue()
    }

    @Test
    fun test_read_token_after_write_token_successfully() {
        val authToken = generateTestAuthToken()

        val result = readWriteTokenDataSource.write(authToken, TokenType.AccessToken)
        assertThat(result).isTrue()

        val token = readWriteTokenDataSource.read(TokenType.AccessToken)
        assertThat(token).isEqualTo(authToken.value)

        val expiration = readWriteTokenDataSource.expiration(TokenType.AccessToken)
        assertThat(expiration).isEqualTo(authToken.expireAt)
    }

    @Test
    fun test_token_expiration_must_be_zero_after_make_it_dirty() {
        val authToken = generateTestAuthToken()

        val result = readWriteTokenDataSource.write(authToken, TokenType.AccessToken)
        assertThat(result).isTrue()

        readWriteTokenDataSource.dirty(TokenType.AccessToken)

        val expiration = readWriteTokenDataSource.expiration(TokenType.AccessToken)
        assertThat(expiration).isEqualTo(0)
    }


    private fun generateTestAuthToken() = AuthToken(
        value = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
        expireAt = System.currentTimeMillis() + 3_600_000L,
    )
}