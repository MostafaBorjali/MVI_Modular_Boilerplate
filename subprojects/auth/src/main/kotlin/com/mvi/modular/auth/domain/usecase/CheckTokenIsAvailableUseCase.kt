package com.mvi.modular.auth.domain.usecase

import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.auth.domain.repository.AuthRepository


internal interface CheckTokenIsAvailableUseCase {
    /**
     *
     */
    operator fun invoke(tokenType: TokenType): Boolean
}


internal class DefaultCheckTokenIsAvailableUseCase(
    private val authRepository: AuthRepository
) : CheckTokenIsAvailableUseCase {

    override fun invoke(tokenType: TokenType): Boolean {
        return authRepository.isTokenAvailable(tokenType)
    }
}