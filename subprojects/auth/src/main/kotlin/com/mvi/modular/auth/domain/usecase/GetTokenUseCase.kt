package com.mvi.modular.auth.domain.usecase

import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.auth.domain.repository.AuthRepository


internal interface GetTokenUseCase {
    /**
     *
     */
    operator fun invoke(tokenType: TokenType): String?
}


internal class DefaultGetTokenUseCase(
    private val authRepository: AuthRepository
) : GetTokenUseCase {

    override fun invoke(tokenType: TokenType): String? {
        return authRepository.getToken(tokenType)
    }
}