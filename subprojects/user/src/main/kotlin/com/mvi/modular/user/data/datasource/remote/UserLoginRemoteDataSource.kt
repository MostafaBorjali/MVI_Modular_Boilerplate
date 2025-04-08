package com.mvi.modular.user.data.datasource.remote

import com.mvi.modular.base.functional.Either
import com.mvi.modular.user.data.datasource.remote.dto.UserRegisterResponseDto
import com.mvi.modular.user.data.datasource.remote.dto.UserVerifyResponseDto


internal interface UserLoginRemoteDataSource {

    /**
     * Send register request to server
     *
     * @param email target email to register
     */
    suspend fun register(email: String): Either<UserRegisterResponseDto, com.mvi.modular.error.domain.model.Error>

    /**
     * Send verification code to server
     *
     * @param email target email to verify
     * @param verificationCode verification code
     */
    suspend fun verify(
        email: String,
        verificationCode: String
    ): Either<UserVerifyResponseDto, com.mvi.modular.error.domain.model.Error>

    /**
     * Sing in with google
     */
    suspend fun google(token: String): Either<UserVerifyResponseDto, com.mvi.modular.error.domain.model.Error>
}