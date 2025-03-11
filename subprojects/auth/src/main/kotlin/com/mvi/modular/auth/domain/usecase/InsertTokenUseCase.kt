package com.mvi.modular.auth.domain.usecase

import com.mvi.modular.auth.domain.model.AuthToken
import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.auth.domain.repository.AuthRepository


internal interface InsertTokenUseCase {
    /**
     *
     */
    operator fun invoke(authToken: AuthToken, tokenType: TokenType): Boolean
}


internal class DefaultInsertTokenUseCase(
    private val authRepository: AuthRepository
) : InsertTokenUseCase {

    override fun invoke(authToken: AuthToken, tokenType: TokenType): Boolean {
        return authRepository.insertToken(authToken, tokenType)
    }
}