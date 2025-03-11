package com.mvi.modular.user.domain.usecase

import com.mvi.modular.base.functional.Either
import com.mvi.modular.user.domain.repository.UserLoginRepository


internal interface UserVerificationUseCase {
    /**
     *
     */
    suspend operator fun invoke(email: String, verificationCode: String): Either<Boolean, Error>
}


internal class DefaultUserVerificationUseCase(
    private val userLoginRepository: UserLoginRepository
) : UserVerificationUseCase {

    override suspend fun invoke(
        email: String,
        verificationCode: String
    ): Either<Boolean, Error> {
        return userLoginRepository.verify(email, verificationCode)
    }
}