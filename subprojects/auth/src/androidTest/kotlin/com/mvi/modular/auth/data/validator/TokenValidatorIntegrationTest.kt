package com.mvi.modular.auth.data.validator

import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.mvi.modular.auth.data.datasource.DefaultReadWriteTokenDataSource
import com.mvi.modular.auth.data.datasource.ReadWriteTokenDataSource
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
import java.util.concurrent.TimeUnit


@MediumTest
class TokenValidatorIntegrationTest : KoinTest {

    @get:Rule(order = 0)
    val koinTestRule = KoinTestRule.create {
        androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
        modules(persistModule(true))
    }

    private val persistService: PersistService by inject()
    private lateinit var readWriteTokenDataSource: ReadWriteTokenDataSource
    private lateinit var tokenValidator: TokenValidator


    @Before
    fun setUp() {
        readWriteTokenDataSource = DefaultReadWriteTokenDataSource(persistService)
        tokenValidator = DefaultTokenValidator(readWriteTokenDataSource)
    }

    @After
    fun tearDown() {
        persistService.removeAll()
    }


    @Test
    fun test_check_token_available_must_returns_true_if_token_exist() {
        val authToken = AuthToken(
            value = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
            expireAt = System.currentTimeMillis() + 3_600_000L,
        )

        val result = readWriteTokenDataSource.write(authToken, TokenType.AccessToken)
        assertThat(result).isTrue()

        val available = tokenValidator.checkTokenAvailable(TokenType.AccessToken)
        assertThat(available).isTrue()
    }

    @Test
    fun test_check_token_available_must_returns_false_if_token_not_exist() {
        val available = tokenValidator.checkTokenAvailable(TokenType.AccessToken)
        assertThat(available).isFalse()
    }

    @Test
    fun test_check_token_is_valid_must_returns_false_if_token_expiration_over() {
        val authToken = AuthToken(
            value = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
            expireAt = System.currentTimeMillis() - 86_400_000L,
        )

        val result = readWriteTokenDataSource.write(authToken, TokenType.AccessToken)
        assertThat(result).isTrue()

        val valid = tokenValidator.checkTokenValid(TokenType.AccessToken)
        assertThat(valid).isFalse()
    }

    @Test
    fun test_check_token_is_valid_must_returns_true_if_token_expiration_not_over() {
        val authToken = AuthToken(
            value = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
            expireAt = System.currentTimeMillis() + 86_400_000L,
        )

        val result = readWriteTokenDataSource.write(authToken, TokenType.AccessToken)
        assertThat(result).isTrue()

        val valid = tokenValidator.checkTokenValid(TokenType.AccessToken)
        assertThat(valid).isTrue()
    }

    @Test
    fun test_check_token_is_valid_with_offset_must_returns_false_if_token_expiration_over() {
        val authToken = AuthToken(
            value = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
            expireAt = System.currentTimeMillis() - 86_400_000L,
        )

        val result = readWriteTokenDataSource.write(authToken, TokenType.AccessToken)
        assertThat(result).isTrue()

        val valid = tokenValidator.checkTokenValidWithOffset(TokenType.AccessToken)
        assertThat(valid).isFalse()
    }

    @Test
    fun test_check_token_is_valid_with_offset_must_returns_true_if_token_expiration_not_over() {
        val authToken = AuthToken(
            value = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
            expireAt = System.currentTimeMillis() + 86_400_000L,
        )

        val result = readWriteTokenDataSource.write(authToken, TokenType.AccessToken)
        assertThat(result).isTrue()

        val valid = tokenValidator.checkTokenValidWithOffset(TokenType.AccessToken)
        assertThat(valid).isTrue()
    }

    @Test
    fun test_check_token_is_valid_until_tomorrow_must_returns_false_if_token_expiration_over() {
        val authToken = AuthToken(
            value = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
            expireAt = System.currentTimeMillis() + 43_200_000L,
        )

        val result = readWriteTokenDataSource.write(authToken, TokenType.AccessToken)
        assertThat(result).isTrue()

        val valid = tokenValidator.checkTokenValidUntil(TokenType.AccessToken, 1, TimeUnit.DAYS)
        assertThat(valid).isFalse()
    }

    @Test
    fun test_check_token_is_valid_until_one_hour_must_returns_true_if_token_expiration_not_over() {
        val authToken = AuthToken(
            value = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
            expireAt = System.currentTimeMillis() + 43_200_000L,
        )

        val result = readWriteTokenDataSource.write(authToken, TokenType.AccessToken)
        assertThat(result).isTrue()

        val valid = tokenValidator.checkTokenValidUntil(TokenType.AccessToken, 1, TimeUnit.HOURS)
        assertThat(valid).isTrue()
    }
}