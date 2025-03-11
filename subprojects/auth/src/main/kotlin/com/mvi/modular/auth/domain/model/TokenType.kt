package com.mvi.modular.auth.domain.model


sealed class TokenType(internal val value: String) {
    /**
     * represent access token
     */
    data object AccessToken : TokenType("acc")

    /**
     * represent refresh token
     */
    data object RefreshToken : TokenType("rfr")
}
