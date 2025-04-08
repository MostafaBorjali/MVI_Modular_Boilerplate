package com.mvi.modular.user.domain.usecase

import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.user.domain.repository.UserLoginRepository


internal interface UserGoogleSignInUseCase {
    /**
     *
     */
    suspend operator fun invoke(token: String): Either<Boolean, Error>
}


internal class DefaultUserGoogleSignInUseCase(
    private val userLoginRepository: UserLoginRepository
) : UserGoogleSignInUseCase {

    override suspend fun invoke(token: String): Either<Boolean, Error> {
        return userLoginRepository.google(token)
    }
}