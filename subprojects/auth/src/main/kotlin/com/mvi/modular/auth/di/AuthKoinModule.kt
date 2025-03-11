package com.mvi.modular.auth.di

import com.mvi.modular.auth.data.datasource.DefaultReadWriteTokenDataSource
import com.mvi.modular.auth.data.datasource.ReadWriteTokenDataSource
import com.mvi.modular.auth.data.repository.DefaultAuthRepository
import com.mvi.modular.auth.data.validator.DefaultTokenValidator
import com.mvi.modular.auth.data.validator.TokenValidator
import com.mvi.modular.auth.domain.usecase.DefaultCheckTokenIsAvailableUseCase
import com.mvi.modular.auth.domain.usecase.DefaultCheckTokenIsValidUntilUseCase
import com.mvi.modular.auth.domain.usecase.DefaultCheckTokenIsValidUseCase
import com.mvi.modular.auth.domain.usecase.DefaultGetTokenUseCase
import com.mvi.modular.auth.domain.usecase.DefaultInsertTokenUseCase
import com.mvi.modular.auth.domain.usecase.DefaultMakeTokenDirtyUseCase
import com.mvi.modular.auth.interceptor.AuthInterceptor
import com.mvi.modular.auth.service.AuthService
import com.mvi.modular.auth.service.DefaultAuthService
import com.mvi.modular.network.core.NetworkConstants.QUALIFIER_AUTH_INTERCEPTOR
import okhttp3.Interceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module


val authModule = module {

    single<ReadWriteTokenDataSource> {
        DefaultReadWriteTokenDataSource(
            persistService = get(),
        )
    }

    single<TokenValidator> {
        DefaultTokenValidator(
            readWriteTokenDataSource = get()
        )
    }

    single<AuthService> {

        val repository = DefaultAuthRepository(
            readWriteTokenDataSource = get(),
            tokenValidator = get()
        )

        val checkTokenIsAvailableUseCase = DefaultCheckTokenIsAvailableUseCase(repository)
        val checkTokenIsValidUseCase = DefaultCheckTokenIsValidUseCase(repository)
        val checkTokenIsValidUntilUseCase = DefaultCheckTokenIsValidUntilUseCase(repository)
        val getTokenUseCase = DefaultGetTokenUseCase(repository)
        val insertTokenUseCase = DefaultInsertTokenUseCase(repository)
        val makeTokenDirtyUseCase = DefaultMakeTokenDirtyUseCase(repository)

        DefaultAuthService(
            checkTokenIsAvailableUseCase = checkTokenIsAvailableUseCase,
            checkTokenIsValidUseCase = checkTokenIsValidUseCase,
            checkTokenIsValidUntilUseCase = checkTokenIsValidUntilUseCase,
            getTokenUseCase = getTokenUseCase,
            insertTokenUseCase = insertTokenUseCase,
            makeTokenDirtyUseCase = makeTokenDirtyUseCase
        )
    }

    single<Interceptor>(named(QUALIFIER_AUTH_INTERCEPTOR)) {
        AuthInterceptor(
            authService = get()
        )
    }
}