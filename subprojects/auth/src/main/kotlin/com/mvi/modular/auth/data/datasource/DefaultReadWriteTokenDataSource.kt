package com.mvi.modular.auth.data.datasource

import com.mvi.modular.auth.core.AuthConstants.READ_WRITE_DEFAULT_OFFSET_VALUE
import com.mvi.modular.auth.core.AuthConstants.READ_WRITE_EXPIRATION_KEY
import com.mvi.modular.auth.core.AuthConstants.READ_WRITE_TOKEN_KEY
import com.mvi.modular.auth.domain.model.AuthToken
import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.persist.service.PersistService


internal class DefaultReadWriteTokenDataSource(
    private val persistService: PersistService,
) : ReadWriteTokenDataSource {


    @Synchronized
    override fun write(authToken: AuthToken, tokenType: TokenType): Boolean {
        val token = persistService.putString(
            "$READ_WRITE_TOKEN_KEY${tokenType.value}",
            authToken.value
        )

        val expiration = persistService.putLong(
            "$READ_WRITE_EXPIRATION_KEY${tokenType.value}",
            authToken.expireAt
        )

        return (token and expiration)
    }

    @Synchronized
    override fun read(tokenType: TokenType): String? {
        return persistService.getString("$READ_WRITE_TOKEN_KEY${tokenType.value}")
    }

    @Synchronized
    override fun expiration(tokenType: TokenType): Long {
        return persistService.getLong("$READ_WRITE_EXPIRATION_KEY${tokenType.value}") ?: 0L
    }

    @Synchronized
    override fun offset(tokenType: TokenType): Long {
        return READ_WRITE_DEFAULT_OFFSET_VALUE
    }

    @Synchronized
    override fun dirty(tokenType: TokenType) {
        persistService.removeKey("$READ_WRITE_TOKEN_KEY${tokenType.value}")
        persistService.putLong("$READ_WRITE_EXPIRATION_KEY${tokenType.value}", 0L)
    }
}