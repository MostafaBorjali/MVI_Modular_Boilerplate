package com.mvi.modular.auth.domain.repository

import com.mvi.modular.auth.domain.model.AuthToken
import com.mvi.modular.auth.domain.model.TokenType
import java.util.concurrent.TimeUnit


internal interface AuthRepository {

    /**
     * Check token is available or not
     */
    fun isTokenAvailable(tokenType: TokenType): Boolean

    /**
     * Check token is valid or not
     */
    fun isTokenValid(tokenType: TokenType, useOffset: Boolean): Boolean

    /**
     * Check token is valid or not
     */
    fun isTokenValid(tokenType: TokenType, time: Long, unit: TimeUnit): Boolean

    /**
     * Get token
     */
    fun getToken(tokenType: TokenType): String?

    /**
     * Create or update token
     */
    fun insertToken(authToken: AuthToken, tokenType: TokenType): Boolean

    /**
     * Make token invalid
     */
    fun makeTokenDirty(tokenType: TokenType)
}