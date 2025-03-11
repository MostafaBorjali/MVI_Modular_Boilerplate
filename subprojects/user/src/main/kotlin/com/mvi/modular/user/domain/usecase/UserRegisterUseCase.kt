package com.mvi.modular.user.domain.usecase

import com.mvi.modular.base.functional.Either
import com.mvi.modular.user.domain.model.UserRegisterInfo
import com.mvi.modular.user.domain.repository.UserLoginRepository
import com.mvi.modular.error.domain.model.Error



internal interface UserRegisterUseCase {
    /**
     *
     */
    suspend operator fun invoke(email: String): Either<UserRegisterInfo, Error>
}


internal class DefaultUserRegisterUseCase(
    private val userLoginRepository: UserLoginRepository
) : UserRegisterUseCase {

    override suspend fun invoke(email: String): Either<UserRegisterInfo, Error> {
        return userLoginRepository.register(email)
    }
}