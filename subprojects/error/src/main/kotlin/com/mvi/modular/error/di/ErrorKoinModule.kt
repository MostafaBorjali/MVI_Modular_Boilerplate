package com.mvi.modular.error.di

import com.mvi.modular.error.data.mapper.DefaultBusinessErrorConverter
import com.mvi.modular.error.device.provider.DefaultErrorMessageProvider
import com.mvi.modular.error.domain.usecase.DefaultConvertErrorToErrorInfoUseCase
import com.mvi.modular.error.domain.usecase.DefaultConvertThrowableToErrorUseCase
import com.mvi.modular.error.service.DefaultErrorService
import com.mvi.modular.error.service.ErrorService
import org.koin.dsl.module


val errorModule = module {


    single<ErrorService> {
        val errorMessageProvider = DefaultErrorMessageProvider(
            localizedStringService = get()
        )
        val businessErrorConverter = DefaultBusinessErrorConverter()
        val convertThrowableToErrorUseCase = DefaultConvertThrowableToErrorUseCase(
            businessErrorConverter = businessErrorConverter
        )
        val convertErrorToErrorInfoUseCase = DefaultConvertErrorToErrorInfoUseCase(
            errorMessageProvider = errorMessageProvider
        )

        DefaultErrorService(
            convertThrowableToErrorUseCase = convertThrowableToErrorUseCase,
            convertErrorToErrorInfoUseCase = convertErrorToErrorInfoUseCase
        )
    }
}