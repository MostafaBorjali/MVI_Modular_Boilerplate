package com.mvi.modular.user.service

import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.user.domain.model.UserRegisterInfo
import com.mvi.modular.user.domain.usecase.UserGoogleSignInUseCase
import com.mvi.modular.user.domain.usecase.UserRegisterUseCase
import com.mvi.modular.user.domain.usecase.UserVerificationUseCase


internal class DefaultUserLoginService(
    private val userRegisterUseCase: UserRegisterUseCase,
    private val userVerificationUseCase: UserVerificationUseCase,
    private val userGoogleSignInUseCase: UserGoogleSignInUseCase,
) : UserLoginService {


    override suspend fun register(email: String): Either<UserRegisterInfo, Error> {
        return userRegisterUseCase(email)
    }

    override suspend fun verify(
        email: String,
        verificationCode: String
    ): Either<Boolean, Error> {
        return userVerificationUseCase(email, verificationCode)
    }

    override suspend fun google(token: String): Either<Boolean, Error> {
        return userGoogleSignInUseCase(token)
    }
}