package com.mvi.modular.auth

import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.mvi.modular.auth.di.authModule
import com.mvi.modular.auth.domain.model.AuthToken
import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.auth.service.AuthService
import com.mvi.modular.persist.di.persistModule
import com.mvi.modular.persist.service.PersistService
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import java.util.concurrent.TimeUnit

@MediumTest
class AuthServiceIntegrationTest : KoinTest {

    @get:Rule(order = 0)
    val koinTestRule = KoinTestRule.create {
        androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
        modules(persistModule(true), authModule)
    }

    private val authService: AuthService by inject()
    private val persistService: PersistService by inject()

    @After
    fun tearDown() {
        persistService.removeAll()
    }


    @Test
    fun test_insert_tokens_then_check_validation_and_get_it_success() {
        val authToken = AuthToken(
            value = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
            expireAt = System.currentTimeMillis() + 86_400_000L,
        )

        val insertResult = authService.insert(authToken, TokenType.AccessToken)
        assertThat(insertResult).isTrue()

        assertThat(authService.isAvailable(TokenType.AccessToken)).isTrue()
        assertThat(authService.isValid(TokenType.AccessToken, true)).isTrue()
        assertThat(authService.isValid(TokenType.AccessToken, 30, TimeUnit.MINUTES)).isTrue()

        val token = authService.token(TokenType.AccessToken)
        assertThat(token).isNotNull()
        assertThat(token).isEqualTo(authToken.value)
    }

    @Test
    fun test_insert_valid_tokens_then_make_it_dirty_success() {
        val authToken = AuthToken(
            value = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
            expireAt = System.currentTimeMillis() + 86_400_000L,
        )

        val insertResult = authService.insert(authToken, TokenType.AccessToken)
        assertThat(insertResult).isTrue()

        authService.dirty(TokenType.AccessToken)
        assertThat(authService.isAvailable(TokenType.AccessToken)).isFalse()
        assertThat(authService.isValid(TokenType.AccessToken, true)).isFalse()
        assertThat(authService.isValid(TokenType.AccessToken, 1, TimeUnit.MINUTES)).isFalse()

        val token = authService.token(TokenType.AccessToken)
        assertThat(token).isNull()
    }

    @Test
    fun test_check_valid_tokens_if_not_exist_success() {
        assertThat(authService.isAvailable(TokenType.RefreshToken)).isFalse()
        assertThat(authService.isValid(TokenType.RefreshToken, true)).isFalse()
        assertThat(authService.isValid(TokenType.RefreshToken, 1, TimeUnit.MINUTES)).isFalse()

        val token = authService.token(TokenType.RefreshToken)
        assertThat(token).isNull()
    }

}