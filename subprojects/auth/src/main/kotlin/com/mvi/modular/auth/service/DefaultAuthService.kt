package com.mvi.modular.auth.service

import com.mvi.modular.auth.domain.model.AuthToken
import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.auth.domain.usecase.CheckTokenIsAvailableUseCase
import com.mvi.modular.auth.domain.usecase.CheckTokenIsValidUntilUseCase
import com.mvi.modular.auth.domain.usecase.CheckTokenIsValidUseCase
import com.mvi.modular.auth.domain.usecase.GetTokenUseCase
import com.mvi.modular.auth.domain.usecase.InsertTokenUseCase
import com.mvi.modular.auth.domain.usecase.MakeTokenDirtyUseCase
import java.util.concurrent.TimeUnit


internal class DefaultAuthService(
    private val checkTokenIsAvailableUseCase: CheckTokenIsAvailableUseCase,
    private val checkTokenIsValidUseCase: CheckTokenIsValidUseCase,
    private val checkTokenIsValidUntilUseCase: CheckTokenIsValidUntilUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val insertTokenUseCase: InsertTokenUseCase,
    private val makeTokenDirtyUseCase: MakeTokenDirtyUseCase
) : AuthService {


    override fun isAvailable(tokenType: TokenType): Boolean {
        return checkTokenIsAvailableUseCase(tokenType)
    }

    override fun isValid(tokenType: TokenType, useOffset: Boolean): Boolean {
        return checkTokenIsValidUseCase(tokenType, useOffset)
    }

    override fun isValid(tokenType: TokenType, time: Long, unit: TimeUnit): Boolean {
        return checkTokenIsValidUntilUseCase(tokenType, time, unit)
    }

    override fun token(tokenType: TokenType): String? {
        return getTokenUseCase(tokenType)
    }

    override fun insert(authToken: AuthToken, tokenType: TokenType): Boolean {
        return insertTokenUseCase(authToken, tokenType)
    }

    override fun dirty(tokenType: TokenType) {
        makeTokenDirtyUseCase(tokenType)
    }
}