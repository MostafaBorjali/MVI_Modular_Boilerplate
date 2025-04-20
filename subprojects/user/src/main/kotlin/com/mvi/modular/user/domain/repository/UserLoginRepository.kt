package com.mvi.modular.user.domain.repository

import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.user.domain.model.UserRegisterInfo


internal interface UserLoginRepository {

    /**
     *
     */
    suspend fun register(email: String): Either<UserRegisterInfo, Error>

    /**
     *
     */
    suspend fun verify(email: String, verificationCode: String): Either<Boolean, Error>

    /**
     *
     */
    suspend fun google(token: String): Either<Boolean, Error>
}