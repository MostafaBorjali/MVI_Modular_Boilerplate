package com.mvi.modular.error.service

import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.error.domain.model.ErrorInfo
import com.mvi.modular.error.domain.usecase.ConvertErrorToErrorInfoUseCase
import com.mvi.modular.error.domain.usecase.ConvertThrowableToErrorUseCase


internal class DefaultErrorService(
    private val convertThrowableToErrorUseCase: ConvertThrowableToErrorUseCase,
    private val convertErrorToErrorInfoUseCase: ConvertErrorToErrorInfoUseCase
) : ErrorService {


    override fun convert(throwable: Throwable): Error {
        return convertThrowableToErrorUseCase(throwable)
    }

    override fun convert(error: Error): ErrorInfo {
        return convertErrorToErrorInfoUseCase(error)
    }
}