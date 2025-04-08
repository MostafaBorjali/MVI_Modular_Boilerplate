package com.mvi.modular.user.domain.usecase

import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.user.domain.model.UserInfo
import com.mvi.modular.user.domain.repository.UserProfileRepository


internal interface UpdateUserProfileUseCase {
    /**
     *
     */
    suspend operator fun invoke(token: String?, lang: String?): Either<UserInfo, Error>
}


internal class DefaultUpdateUserProfileUseCase(
    private val userProfileRepository: UserProfileRepository,
) : UpdateUserProfileUseCase {

    override suspend fun invoke(token: String?, lang: String?): Either<UserInfo, Error> {
        return userProfileRepository.updateUserProfile(token, lang)
    }
}