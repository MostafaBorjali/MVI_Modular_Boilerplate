package com.mvi.modular.auth.service

import com.mvi.modular.auth.domain.model.AuthToken
import com.mvi.modular.auth.domain.model.TokenType
import java.util.concurrent.TimeUnit


interface AuthService {

    /**
     * Check target token is available or not in persist storage
     *
     * @param tokenType Target token type
     * @return true if target token is available, false otherwise
     */
    fun isAvailable(tokenType: TokenType): Boolean

    /**
     * Check target token is valid or not
     *
     * @param tokenType Target token type
     * @param useOffset Use offset to measure validation or not
     * @return true if token exist and valid, false otherwise
     */
    fun isValid(tokenType: TokenType, useOffset: Boolean): Boolean

    /**
     * Check target token is valid until reach the time or not
     *
     * @param tokenType Target token type
     * @param time Target time
     * @param unit Target time unit to check
     * @return true if token exist and valid, false otherwise
     */
    fun isValid(tokenType: TokenType, time: Long, unit: TimeUnit): Boolean

    /**
     * Get target token
     *
     * @param tokenType Target token type
     * @return valid token, otherwise return null
     */
    fun token(tokenType: TokenType): String?

    /**
     * Create or update token in persist storage (encrypted)
     *
     * @param authToken Target Token
     * @param tokenType Target token type
     * @return true if target token insert successfully, false otherwise
     */
    fun insert(authToken: AuthToken, tokenType: TokenType): Boolean

    /**
     * Makes target token invalid
     *
     * @param tokenType Target token type
     */
    fun dirty(tokenType: TokenType)
}