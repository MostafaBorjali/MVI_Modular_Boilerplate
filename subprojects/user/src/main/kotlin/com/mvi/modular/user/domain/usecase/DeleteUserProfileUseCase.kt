package com.mvi.modular.user.domain.usecase

import com.mvi.modular.base.functional.Either
import com.mvi.modular.user.domain.repository.UserProfileRepository


internal interface DeleteUserProfileUseCase {
    /**
     *
     */
    suspend operator fun invoke(): Either<Unit, Error>
}


internal class DefaultDeleteUserProfileUseCase(
    private val userProfileRepository: UserProfileRepository,
) : DeleteUserProfileUseCase {

    override suspend fun invoke(): Either<Unit, Error> {
        return userProfileRepository.delete()
    }
}