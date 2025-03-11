package com.mvi.modular.auth.interceptor

import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.auth.service.AuthService
import com.mvi.modular.error.domain.exception.AccessTokenExpiredException
import com.mvi.modular.error.domain.exception.AccessTokenNotFoundException
import com.mvi.modular.network.annotation.withoutAuthentication
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthInterceptor(
    private val authService: AuthService
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (chain.request().withoutAuthentication()) {
            return chain.proceed(chain.request())
        }

        if (!authService.isAvailable(TokenType.AccessToken)) {
            throw AccessTokenNotFoundException()
        }

        //
        // use this section after add refresh token flow
        //
//        if (!authService.isAvailable(TokenType.RefreshToken)) {
//            throw RefreshTokenNotFoundException()
//        }

        if (!authService.isValid(TokenType.AccessToken, true)) {
            //
            // we should first try refresh token here and after that
            // if not success then throw exception, otherwise continue flow,
            // in this meaning time we should logout user
            //
            throw AccessTokenExpiredException()
        }

        //
        // Add access token to target request
        //
        val token = authService.token(TokenType.AccessToken)
            ?: throw throw AccessTokenExpiredException()

        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer $token")

        //
        // check if response is (401 Unauthorized)
        //
        val response = chain.proceed(request.build())
        if (response.code == 401) {
            //
            // we need to silently refresh current access token
            // if we successfully refresh it then retry current request
            // otherwise throw access token error
            //
            throw throw AccessTokenExpiredException()
        }
        return response
    }
}