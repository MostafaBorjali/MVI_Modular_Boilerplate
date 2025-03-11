package com.mvi.modular.auth.data.datasource

import com.mvi.modular.auth.core.AuthConstants.READ_WRITE_DEFAULT_OFFSET_VALUE
import com.mvi.modular.auth.core.AuthConstants.READ_WRITE_EXPIRATION_KEY
import com.mvi.modular.auth.domain.model.AuthToken
import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.persist.service.PersistService
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.anyLong
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


class ReadWriteTokenDataSourceTest {

    private lateinit var readWriteTokenDataSource: ReadWriteTokenDataSource
    private lateinit var persistService: PersistService


    @Before
    fun setUp() {
        persistService = mock(PersistService::class.java)
        readWriteTokenDataSource = DefaultReadWriteTokenDataSource(persistService)
    }

    @Test
    fun `test write token must returns true if all element of token write successfully`() {
        val authToken = generateTestAuthToken()
        `when`(persistService.putString(anyString(), anyString())).thenReturn(true)
        `when`(persistService.putLong(anyString(), anyLong())).thenReturn(true)
        `when`(persistService.putInt(anyString(), anyInt())).thenReturn(true)

        val result = readWriteTokenDataSource.write(authToken, TokenType.AccessToken)

        assertThat(result).isTrue()
    }

    @Test
    fun `test write token must returns false if any element of token write unsuccessfully`() {
        val authToken = generateTestAuthToken()

        `when`(persistService.putString(anyString(), anyString())).thenReturn(false)
        `when`(persistService.putLong(anyString(), anyLong())).thenReturn(true)
        `when`(persistService.putInt(anyString(), anyInt())).thenReturn(true)

        val result = readWriteTokenDataSource.write(authToken, TokenType.AccessToken)

        assertThat(result).isFalse()
    }

    @Test
    fun `test read token must returns null if token not found in persist storage`() {
        `when`(persistService.getString(anyString())).thenReturn(null)
        val result = readWriteTokenDataSource.read(TokenType.AccessToken)
        assertThat(result).isNull()
    }

    @Test
    fun `test read token must returns token successfully`() {
        `when`(persistService.getString(anyString())).thenReturn("token")

        val result = readWriteTokenDataSource.read(TokenType.AccessToken)
        assertThat(result).isEqualTo("token")
    }

    @Test
    fun `test read expiration must returns zero if expiration not found in persist storage`() {
        `when`(persistService.getLong(anyString())).thenReturn(null)

        val result = readWriteTokenDataSource.expiration(TokenType.AccessToken)
        assertThat(result).isEqualTo(0L)
    }

    @Test
    fun `test read offset must returns default value if offset not found in persist storage`() {
        `when`(persistService.getInt(anyString())).thenReturn(null)

        val result = readWriteTokenDataSource.offset(TokenType.AccessToken)
        assertThat(result).isEqualTo(READ_WRITE_DEFAULT_OFFSET_VALUE)
    }

    @Test
    fun `test make token dirty must call persist storage with zero value for expiration`() {
        readWriteTokenDataSource.dirty(TokenType.AccessToken)
        verify(persistService, times(1))
            .putLong("${READ_WRITE_EXPIRATION_KEY}${TokenType.AccessToken.value}", 0L)
    }


    private fun generateTestAuthToken() = AuthToken(
        value = "test_token",
        expireAt = 1,
    )
}