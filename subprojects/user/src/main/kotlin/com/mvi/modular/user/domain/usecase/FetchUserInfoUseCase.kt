package com.mvi.modular.user.domain.usecase

import com.mvi.modular.base.functional.Either
import com.mvi.modular.user.domain.model.UserInfo
import com.mvi.modular.user.domain.repository.UserProfileRepository


internal interface FetchUserInfoUseCase {
    /**
     *
     */
    suspend operator fun invoke(): Either<UserInfo, Error>
}


internal class DefaultFetchUserInfoUseCase(
    private val userProfileRepository: UserProfileRepository
) : FetchUserInfoUseCase {

    override suspend fun invoke(): Either<UserInfo, Error> {
        return userProfileRepository.fetchUser()
    }
}
