package com.mvi.modular.user.domain.usecase

import com.mvi.modular.user.domain.repository.UserProfileRepository


internal interface LogoutUserUseCase {
    /**
     *
     */
    suspend operator fun invoke(): Boolean
}


internal class DefaultLogoutUserUseCase(
    private val userProfileRepository: UserProfileRepository
) : LogoutUserUseCase {

    override suspend fun invoke(): Boolean {
        return userProfileRepository.logoutUser()
    }
}