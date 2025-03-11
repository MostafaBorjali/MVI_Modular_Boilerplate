package com.mvi.modular.auth.domain.usecase

import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.auth.domain.repository.AuthRepository
import java.util.concurrent.TimeUnit


internal interface CheckTokenIsValidUntilUseCase {
    /**
     *
     */
    operator fun invoke(tokenType: TokenType, time: Long, unit: TimeUnit): Boolean
}


internal class DefaultCheckTokenIsValidUntilUseCase(
    private val authRepository: AuthRepository
) : CheckTokenIsValidUntilUseCase {

    override fun invoke(tokenType: TokenType, time: Long, unit: TimeUnit): Boolean {
        return authRepository.isTokenValid(tokenType, time, unit)
    }
}