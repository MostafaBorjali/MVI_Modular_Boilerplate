package com.mvi.modular.user.service

import com.mvi.modular.base.functional.Either
import com.mvi.modular.user.domain.model.UserInfo
import com.mvi.modular.user.domain.usecase.DeleteUserProfileUseCase
import com.mvi.modular.user.domain.usecase.FetchUserInfoUseCase
import com.mvi.modular.user.domain.usecase.GetUserInfoUseCase
import com.mvi.modular.user.domain.usecase.LogoutUserUseCase
import com.mvi.modular.user.domain.usecase.UpdateUserProfileUseCase


internal class DefaultUserProfileService(
    private val fetchUserInfoUseCase: FetchUserInfoUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase,
    private val deleteUserProfileUseCase: DeleteUserProfileUseCase,
) : UserProfileService {


    override suspend fun fetchUser(): Either<UserInfo, Error> {
        return fetchUserInfoUseCase()
    }

    override suspend fun getUser(): UserInfo? {
        return getUserInfoUseCase()
    }

    override suspend fun isLogin(): Boolean {
        return getUserInfoUseCase() != null
    }

    override suspend fun logout(): Boolean {
        return logoutUserUseCase()
    }

    override suspend fun updateUserProfile(
        token: String?,
        lang: String?,
    ): Either<UserInfo, Error> {
        return updateUserProfileUseCase(token, lang)
    }

    override suspend fun delete(): Either<Unit, Error> {
        return deleteUserProfileUseCase()
    }
}