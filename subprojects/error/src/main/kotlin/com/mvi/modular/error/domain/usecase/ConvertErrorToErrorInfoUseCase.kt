package com.mvi.modular.error.domain.usecase

import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.error.domain.model.ErrorInfo
import com.mvi.modular.error.domain.provider.ErrorMessageProvider


internal interface ConvertErrorToErrorInfoUseCase {
    /**
     *
     */
    operator fun invoke(error: Error): ErrorInfo
}


internal class DefaultConvertErrorToErrorInfoUseCase(
    private val errorMessageProvider: ErrorMessageProvider
) : ConvertErrorToErrorInfoUseCase {

    override fun invoke(error: Error): ErrorInfo {
        return when (error) {
            is Error.NetworkError.ConnectionError,
            is Error.NetworkError.RequestNotValid,
            is Error.NetworkError.HeaderNotFound,
            is Error.NetworkError.BadRequest,
            is Error.NetworkError.NotFound,
            is Error.NetworkError.InternalServerError,
            is Error.NetworkError.InputNotValid -> {
                ErrorInfo(
                    message = error.errorMessage ?: errorMessageProvider.message(error),
                    code = error.errorCode,
                    cancelable = true,
                    retryable = true
                )
            }

            is Error.AuthError.AccessTokenNotFound,
            is Error.AuthError.AccessTokenExpired,
            is Error.AuthError.RefreshTokenNotFound -> {
                ErrorInfo(
                    message = error.errorMessage ?: errorMessageProvider.message(error),
                    code = error.errorCode,
                    cancelable = false,
                    retryable = false,
                    reLogin = true
                )
            }

            is Error.AuthError.ForbiddenRequest -> {
                ErrorInfo(
                    message = error.errorMessage ?: errorMessageProvider.message(error),
                    code = error.errorCode,
                    cancelable = true,
                    retryable = true
                )
            }

            is Error.UnknownError -> {
                ErrorInfo(
                    message = error.errorMessage ?: errorMessageProvider.message(error),
                    code = error.errorCode,
                    cancelable = true,
                    retryable = true
                )
            }
        }
    }
}