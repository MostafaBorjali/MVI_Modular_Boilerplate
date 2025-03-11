package com.mvi.modular.error.domain.usecase

import com.mvi.modular.error.core.ErrorConstants.ERROR_CODE_400
import com.mvi.modular.error.core.ErrorConstants.ERROR_CODE_401
import com.mvi.modular.error.core.ErrorConstants.ERROR_CODE_403
import com.mvi.modular.error.core.ErrorConstants.ERROR_CODE_404
import com.mvi.modular.error.core.ErrorConstants.ERROR_CODE_422
import com.mvi.modular.error.core.ErrorConstants.ERROR_CODE_500
import com.mvi.modular.error.domain.exception.AccessTokenExpiredException
import com.mvi.modular.error.domain.exception.AccessTokenNotFoundException
import com.mvi.modular.error.domain.exception.RefreshTokenNotFoundException
import com.mvi.modular.error.domain.mapper.BusinessErrorConverter
import com.mvi.modular.error.domain.model.Error
import retrofit2.HttpException
import java.io.IOException


internal interface ConvertThrowableToErrorUseCase {
    /**
     *
     */
    operator fun invoke(throwable: Throwable): Error
}


internal class DefaultConvertThrowableToErrorUseCase(
    private val businessErrorConverter: BusinessErrorConverter
) : ConvertThrowableToErrorUseCase {

    override fun invoke(throwable: Throwable): Error {
        when (throwable) {

            /*
             * Network section
             */
            is HttpException -> {
                val response = throwable.response() ?: return Error.UnknownError()
                val code = response.code()
                val body = response.errorBody()?.string()
                if (body != null) {
                    val businessError = businessErrorConverter.convert(body)
                    if (businessError != null) {
                        if (code == ERROR_CODE_400) {
                            return Error.NetworkError.BadRequest(
                                businessError.message,
                                businessError.internalCode
                            )
                        }

                        if (code == ERROR_CODE_401) {
                            return Error.AuthError.AccessTokenExpired(
                                businessError.message,
                                businessError.internalCode
                            )
                        }

                        if (code == ERROR_CODE_403) {
                            return Error.AuthError.ForbiddenRequest(
                                businessError.message,
                                businessError.internalCode
                            )
                        }

                        if (code == ERROR_CODE_404) {
                            return Error.NetworkError.NotFound(
                                businessError.message,
                                businessError.internalCode
                            )
                        }

                        if (code == ERROR_CODE_422) {
                            return Error.NetworkError.InputNotValid(
                                businessError.message,
                                businessError.internalCode
                            )
                        }

                        if (code == ERROR_CODE_500) {
                            return Error.NetworkError.InternalServerError(
                                businessError.message,
                                businessError.internalCode
                            )
                        }
                        return Error.UnknownError(businessError.message, businessError.internalCode)
                    } else {
                        return Error.UnknownError()
                    }
                } else {
                    return Error.UnknownError()
                }
            }

            /*
             * Authentication section
             */
            is AccessTokenNotFoundException -> return Error.AuthError.AccessTokenNotFound()
            is AccessTokenExpiredException -> return Error.AuthError.AccessTokenExpired()
            is RefreshTokenNotFoundException -> return Error.AuthError.RefreshTokenNotFound()

            /*
             * Network
             */
            is IOException -> return Error.NetworkError.ConnectionError()

            /*
             * Otherwise
             */
            else -> return Error.UnknownError()
        }
    }
}