package com.mvi.modular.error.domain.model


sealed class Error(val errorMessage: String? = null, val errorCode: Int) {

    /**
     * Network Errors
     */
    sealed class NetworkError(errorMessage: String?, errorCode: Int) :
        Error(errorMessage, errorCode) {

        /**
         *
         */
        data class ConnectionError(val message: String? = null, val code: Int = 0) :
            NetworkError(message, code)

        /**
         *
         */
        data class RequestNotValid(val message: String? = null, val code: Int = 0) :
            NetworkError(message, code)

        /**
         *
         */
        data class HeaderNotFound(val message: String? = null, val code: Int = 0) :
            NetworkError(message, code)

        /**
         *
         */
        data class BadRequest(val message: String? = null, val code: Int = 0) :
            NetworkError(message, code)

        /**
         *
         */
        data class NotFound(val message: String? = null, val code: Int = 0) :
            NetworkError(message, code)

        /**
         *
         */
        data class InternalServerError(val message: String? = null, val code: Int = 0) :
            NetworkError(message, code)

        /**
         *
         */
        data class InputNotValid(val message: String? = null, val code: Int = 0) :
            NetworkError(message, code)
    }

    /**
     * Authentication Errors
     */
    sealed class AuthError(errorMessage: String?, errorCode: Int) : Error(errorMessage, errorCode) {

        /**
         *
         */
        data class AccessTokenNotFound(val message: String? = null, val code: Int = 0) :
            AuthError(message, code)

        /**
         *
         */
        data class AccessTokenExpired(val message: String? = null, val code: Int = 0) :
            AuthError(message, code)

        /**
         *
         */
        data class RefreshTokenNotFound(val message: String? = null, val code: Int = 0) :
            AuthError(message, code)

        /**
         *
         */
        data class ForbiddenRequest(val message: String? = null, val code: Int = 0) :
            AuthError(message, code)
    }

    /**
     *
     */
    data class UnknownError(val message: String? = null, val code: Int = 0) : Error(message, code)
}