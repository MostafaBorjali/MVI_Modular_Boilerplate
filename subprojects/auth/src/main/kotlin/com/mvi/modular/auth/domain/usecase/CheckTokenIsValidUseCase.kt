package com.mvi.modular.auth.domain.usecase

import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.auth.domain.repository.AuthRepository


internal interface CheckTokenIsValidUseCase {
    /**
     *
     */
    operator fun invoke(tokenType: TokenType, useOffset: Boolean): Boolean
}


internal class DefaultCheckTokenIsValidUseCase(
    private val authRepository: AuthRepository
) : CheckTokenIsValidUseCase {

    override fun invoke(tokenType: TokenType, useOffset: Boolean): Boolean {
        return authRepository.isTokenValid(tokenType, useOffset)
    }
}