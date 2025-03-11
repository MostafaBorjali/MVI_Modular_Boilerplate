package com.mvi.modular.auth.data.validator


import com.mvi.modular.auth.domain.model.TokenType
import java.util.concurrent.TimeUnit


internal interface TokenValidator {

    /**
     *
     */
    fun checkTokenAvailable(tokenType: TokenType): Boolean

    /**
     *
     */
    fun checkTokenValid(tokenType: TokenType): Boolean

    /**
     *
     */
    fun checkTokenValidWithOffset(tokenType: TokenType): Boolean

    /**
     *
     */
    fun checkTokenValidUntil(tokenType: TokenType, time: Long, unit: TimeUnit): Boolean
}