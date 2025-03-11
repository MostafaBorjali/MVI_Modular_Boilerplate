package com.mvi.modular.user.data.datasource.remote

import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.extension.launchApiCatchingError
import com.mvi.modular.error.service.ErrorService
import com.mvi.modular.user.data.datasource.remote.dto.UserGoogleLoginRequestDto
import com.mvi.modular.user.data.datasource.remote.dto.UserRegisterRequestDto
import com.mvi.modular.user.data.datasource.remote.dto.UserRegisterResponseDto
import com.mvi.modular.user.data.datasource.remote.dto.UserVerifyRequestDto
import com.mvi.modular.user.data.datasource.remote.dto.UserVerifyResponseDto


internal class DefaultUserLoginRemoteDataSource(
    private val userLoginApi: UserLoginApi,
    private val errorService: ErrorService,
) : UserLoginRemoteDataSource {


    override suspend fun register(email: String): Either<UserRegisterResponseDto, Error> {
        return errorService.launchApiCatchingError {
            userLoginApi.register(body = UserRegisterRequestDto(email)).response
        }
    }

    override suspend fun verify(
        email: String,
        verificationCode: String
    ): Either<UserVerifyResponseDto, Error> {
        return errorService.launchApiCatchingError {
            userLoginApi.verify(body = UserVerifyRequestDto(email, verificationCode)).response
        }
    }

    override suspend fun google(token: String): Either<UserVerifyResponseDto, Error> {
        return errorService.launchApiCatchingError {
            userLoginApi.googleLogin(
                UserGoogleLoginRequestDto(name = "google", token = token)
            ).response
        }
    }
}