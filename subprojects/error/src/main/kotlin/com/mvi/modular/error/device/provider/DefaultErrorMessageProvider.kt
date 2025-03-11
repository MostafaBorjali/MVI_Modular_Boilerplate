package com.mvi.modular.error.device.provider

import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.error.domain.provider.ErrorMessageProvider
import com.mvi.modular.lang.service.LocalizedStringService
import com.mvi.modular.strings.core.strings


internal class DefaultErrorMessageProvider(
    private val localizedStringService: LocalizedStringService
) : ErrorMessageProvider {


    override fun message(error: Error): String {
        val id = when (error) {
            is Error.NetworkError.ConnectionError -> strings.general_connection_error
            is Error.NetworkError.InternalServerError -> strings.general_internal_server_error
            is Error.NetworkError.RequestNotValid,
            is Error.NetworkError.HeaderNotFound,
            is Error.NetworkError.BadRequest,
            is Error.NetworkError.NotFound,
            is Error.NetworkError.InputNotValid,
            is Error.AuthError.ForbiddenRequest -> strings.general_request_invalid_error

            is Error.AuthError.AccessTokenNotFound -> strings.auth_access_token_not_found_error
            is Error.AuthError.AccessTokenExpired -> strings.auth_access_token_expired_error
            is Error.AuthError.RefreshTokenNotFound -> strings.auth_refresh_token_not_found_error

            is Error.UnknownError -> strings.general_unknown_error
        }

        return localizedStringService.stringResource(id)
    }
}