package com.mvi.modular.user.domain.usecase

import com.mvi.modular.user.domain.model.UserInfo
import com.mvi.modular.user.domain.repository.UserProfileRepository


internal interface GetUserInfoUseCase {
    /**
     *
     */
    suspend operator fun invoke(): UserInfo?
}


internal class DefaultGetUseInfoUseCase(
    private val userProfileRepository: UserProfileRepository
) : GetUserInfoUseCase {

    override suspend fun invoke(): UserInfo? {
        return userProfileRepository.getUser()
    }
}