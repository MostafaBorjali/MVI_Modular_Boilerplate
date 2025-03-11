package com.mvi.modular.auth.data.validator

import com.mvi.modular.auth.data.datasource.ReadWriteTokenDataSource
import com.mvi.modular.auth.domain.model.TokenType
import java.util.concurrent.TimeUnit


internal class DefaultTokenValidator(
    private val readWriteTokenDataSource: ReadWriteTokenDataSource
) : TokenValidator {


    override fun checkTokenAvailable(tokenType: TokenType): Boolean {
        val token = readWriteTokenDataSource.read(tokenType)
        return !token.isNullOrEmpty()
    }

    override fun checkTokenValid(tokenType: TokenType): Boolean {
        return localCheckTokenValid(tokenType)
    }

    override fun checkTokenValidWithOffset(tokenType: TokenType): Boolean {
        val offset = readWriteTokenDataSource.offset(tokenType)
        return localCheckTokenValid(tokenType, offset, TimeUnit.SECONDS)
    }

    override fun checkTokenValidUntil(
        tokenType: TokenType,
        time: Long,
        unit: TimeUnit
    ): Boolean {
        return localCheckTokenValid(tokenType, time, unit)
    }

    private fun localCheckTokenValid(
        tokenType: TokenType,
        time: Long? = null,
        unit: TimeUnit? = null
    ): Boolean {
        val expiration = TimeUnit.SECONDS.toMillis(readWriteTokenDataSource.expiration(tokenType))
        val now = System.currentTimeMillis()
        if (expiration <= 0) return false

        return if (time != null && unit != null) {
            val offset = unit.toMillis(time)
            (expiration - offset) > now
        } else {
            expiration > now
        }
    }
}