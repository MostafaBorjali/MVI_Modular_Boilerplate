package com.mvi.modular.auth.data.validator

import com.mvi.modular.auth.core.AuthConstants.READ_WRITE_DEFAULT_OFFSET_VALUE
import com.mvi.modular.auth.data.datasource.ReadWriteTokenDataSource
import com.mvi.modular.auth.domain.model.TokenType
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.util.concurrent.TimeUnit


class TokenValidatorTest {

    private lateinit var tokenValidator: TokenValidator
    private lateinit var readWriteTokenDataSource: ReadWriteTokenDataSource


    @Before
    fun setUp() {
        readWriteTokenDataSource = mock(ReadWriteTokenDataSource::class.java)
        tokenValidator = DefaultTokenValidator(readWriteTokenDataSource)
    }


    @Test
    fun `test check token is available must returns true if token exist`() {
        `when`(readWriteTokenDataSource.read(TokenType.AccessToken)).thenReturn("token")

        val result = tokenValidator.checkTokenAvailable(TokenType.AccessToken)
        assertThat(result).isTrue()
    }

    @Test
    fun `test check token is available must returns false if token not exist`() {
        `when`(readWriteTokenDataSource.read(TokenType.AccessToken)).thenReturn(null)

        val result = tokenValidator.checkTokenAvailable(TokenType.AccessToken)
        assertThat(result).isFalse()
    }

    @Test
    fun `test check token is available must returns false if token is empty`() {
        `when`(readWriteTokenDataSource.read(TokenType.AccessToken)).thenReturn("")

        val result = tokenValidator.checkTokenAvailable(TokenType.AccessToken)
        assertThat(result).isFalse()
    }

    @Test
    fun `test check token is valid must returns false if token expiration is zero`() {
        `when`(readWriteTokenDataSource.expiration(TokenType.AccessToken)).thenReturn(0)

        val result = tokenValidator.checkTokenValid(TokenType.AccessToken)
        assertThat(result).isFalse()
    }

    @Test
    fun `test check token is valid must returns false if token expiration is over`() {
        `when`(readWriteTokenDataSource.expiration(TokenType.AccessToken)).thenReturn(
            System.currentTimeMillis() - 86_400_000
        )

        val result = tokenValidator.checkTokenValid(TokenType.AccessToken)
        assertThat(result).isFalse()
    }

    @Test
    fun `test check token is valid must returns true if token expiration is not over`() {
        `when`(readWriteTokenDataSource.expiration(TokenType.AccessToken)).thenReturn(
            System.currentTimeMillis() + 86_400_000
        )

        val result = tokenValidator.checkTokenValid(TokenType.AccessToken)
        assertThat(result).isTrue()
    }

    @Test
    fun `test check token is valid with offset must returns false if token expiration is not over but offset works`() {
        `when`(readWriteTokenDataSource.expiration(TokenType.AccessToken)).thenReturn(
            System.currentTimeMillis() + (READ_WRITE_DEFAULT_OFFSET_VALUE * 1000L / 2)
        )
        `when`(readWriteTokenDataSource.offset(TokenType.AccessToken)).thenReturn(
            READ_WRITE_DEFAULT_OFFSET_VALUE
        )

        val result = tokenValidator.checkTokenValidWithOffset(TokenType.AccessToken)
        assertThat(result).isFalse()
    }

    @Test
    fun `test check token is valid with offset must returns true if token expiration with offset is not over`() {
        `when`(readWriteTokenDataSource.expiration(TokenType.AccessToken)).thenReturn(
            System.currentTimeMillis() + (READ_WRITE_DEFAULT_OFFSET_VALUE * 1000L * 2)
        )
        `when`(readWriteTokenDataSource.offset(TokenType.AccessToken)).thenReturn(
            READ_WRITE_DEFAULT_OFFSET_VALUE
        )

        val result = tokenValidator.checkTokenValidWithOffset(TokenType.AccessToken)
        assertThat(result).isTrue()
    }

    @Test
    fun `test check token is valid until must returns true if token expiration is not over until input time`() {
        `when`(readWriteTokenDataSource.expiration(TokenType.AccessToken)).thenReturn(
            System.currentTimeMillis() + (READ_WRITE_DEFAULT_OFFSET_VALUE * 1000L * 2)
        )

        val result = tokenValidator.checkTokenValidUntil(
            TokenType.AccessToken,
            READ_WRITE_DEFAULT_OFFSET_VALUE,
            TimeUnit.SECONDS
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `test check token is valid until must returns false if token expiration is over until input time`() {
        `when`(readWriteTokenDataSource.expiration(TokenType.AccessToken)).thenReturn(
            System.currentTimeMillis() + (READ_WRITE_DEFAULT_OFFSET_VALUE * 1000L * 2)
        )

        val result = tokenValidator.checkTokenValidUntil(
            TokenType.AccessToken,
            READ_WRITE_DEFAULT_OFFSET_VALUE * 10,
            TimeUnit.SECONDS
        )
        assertThat(result).isFalse()
    }

}