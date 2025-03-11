package com.mvi.modular.auth.data.repository

import com.mvi.modular.auth.data.datasource.ReadWriteTokenDataSource
import com.mvi.modular.auth.data.validator.TokenValidator
import com.mvi.modular.auth.domain.model.AuthToken
import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.auth.domain.repository.AuthRepository
import java.util.concurrent.TimeUnit


internal class DefaultAuthRepository(
    private val readWriteTokenDataSource: ReadWriteTokenDataSource,
    private val tokenValidator: TokenValidator
) : AuthRepository {


    override fun isTokenAvailable(tokenType: TokenType): Boolean {
        return tokenValidator.checkTokenAvailable(tokenType)
    }

    override fun isTokenValid(tokenType: TokenType, useOffset: Boolean): Boolean {
        return if (useOffset) {
            tokenValidator.checkTokenValidWithOffset(tokenType)
        } else {
            tokenValidator.checkTokenValid(tokenType)
        }
    }

    override fun isTokenValid(tokenType: TokenType, time: Long, unit: TimeUnit): Boolean {
        return tokenValidator.checkTokenValidUntil(tokenType, time, unit)
    }

    override fun getToken(tokenType: TokenType): String? {
        return readWriteTokenDataSource.read(tokenType)
    }

    override fun insertToken(authToken: AuthToken, tokenType: TokenType): Boolean {
        return readWriteTokenDataSource.write(authToken, tokenType)
    }

    override fun makeTokenDirty(tokenType: TokenType) {
        readWriteTokenDataSource.dirty(tokenType)
    }
}