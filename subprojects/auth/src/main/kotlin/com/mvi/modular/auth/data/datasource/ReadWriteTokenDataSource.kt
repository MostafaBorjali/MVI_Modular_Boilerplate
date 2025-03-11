package com.mvi.modular.auth.data.datasource

import com.mvi.modular.auth.domain.model.AuthToken
import com.mvi.modular.auth.domain.model.TokenType


internal interface ReadWriteTokenDataSource {

    /**
     * Write token in persist storage (encrypted and synchronized)
     *
     * @return true if write successfully
     */
    fun write(authToken: AuthToken, tokenType: TokenType): Boolean

    /**
     * Read token from persist storage (decrypted and synchronized)
     *
     * @return token if exist, null otherwise
     */
    fun read(tokenType: TokenType): String?

    /**
     * Get expiration time of token
     *
     * @return expiration time, zero if not exist
     */
    fun expiration(tokenType: TokenType): Long

    /**
     * Get offset time of token (refresh reason)
     *
     * @return offset time, default value if offset not exist
     */
    fun offset(tokenType: TokenType): Long

    /**
     * Makes target token invalid
     */
    fun dirty(tokenType: TokenType)
}