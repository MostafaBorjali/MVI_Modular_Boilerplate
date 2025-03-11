package com.mvi.modular.auth.domain.usecase

import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.auth.domain.repository.AuthRepository


internal interface MakeTokenDirtyUseCase {
    /**
     *
     */
    operator fun invoke(tokenType: TokenType)
}


internal class DefaultMakeTokenDirtyUseCase(
    private val authRepository: AuthRepository
) : MakeTokenDirtyUseCase {

    override fun invoke(tokenType: TokenType) {
        authRepository.makeTokenDirty(tokenType)
    }
}