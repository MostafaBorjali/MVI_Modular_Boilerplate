package com.mvi.modular.user.service

import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.user.domain.model.UserRegisterInfo


interface UserLoginService {

    /**
     * Register request to get verification code for target email
     *
     * @param email target email to register
     */
    suspend fun register(email: String): Either<UserRegisterInfo, Error>

    /**
     * Verify target email with verification code
     *
     * @param email target email to verify
     * @param verificationCode target verification code related to email
     */
    suspend fun verify(email: String, verificationCode: String): Either<Boolean, Error>

    /**
     * Sing in user with google accounts
     *
     * @param token google credentials token
     */
    suspend fun google(token: String): Either<Boolean,Error>
}