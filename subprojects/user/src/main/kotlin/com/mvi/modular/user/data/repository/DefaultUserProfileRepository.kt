package com.mvi.modular.user.data.repository

import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.auth.service.AuthService
import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.persist.core.PersistConstants.KEY_DEFAULT_ESIM_ID
import com.mvi.modular.persist.service.PersistService
import com.mvi.modular.user.core.UserConstants.KEY_USER_INFO
import com.mvi.modular.user.data.datasource.remote.UserProfileRemoteDataSource
import com.mvi.modular.user.data.mapper.toJson
import com.mvi.modular.user.data.mapper.toUserInfo
import com.mvi.modular.user.data.mapper.toUserInfoDto
import com.mvi.modular.user.domain.model.UserInfo
import com.mvi.modular.user.domain.repository.UserProfileRepository


internal class DefaultUserProfileRepository(
    private val userProfileRemoteDataSource: UserProfileRemoteDataSource,
    private val persistService: PersistService,
    private val authService: AuthService,
) : UserProfileRepository {


    override suspend fun fetchUser(): Either<UserInfo, Error> {
        return when (val response = userProfileRemoteDataSource.profile()) {
            is Either.Success -> {
                persistService.putString(
                    KEY_USER_INFO,
                    response.data.toJson()
                )

                Either.Success(response.data.toUserInfo())
            }

            is Either.Error -> {
                Either.Error(response.error)
            }
        }
    }

    override suspend fun getUser(): UserInfo? {
        val userJson = persistService.getString(KEY_USER_INFO)
        return userJson?.toUserInfoDto()?.toUserInfo()
    }

    override suspend fun logoutUser(): Boolean {
        authService.dirty(TokenType.AccessToken)
        return persistService.removeKey(KEY_USER_INFO) &&
                persistService.removeKey(KEY_DEFAULT_ESIM_ID)
    }

    override suspend fun updateUserProfile(
        token: String?,
        lang: String?,
    ): Either<UserInfo, Error> {
        return when (val response = userProfileRemoteDataSource.update(token, lang)) {
            is Either.Success -> {
                persistService.putString(
                    KEY_USER_INFO,
                    response.data.toJson()
                )

                Either.Success(response.data.toUserInfo())
            }

            is Either.Error -> {
                Either.Error(response.error)
            }
        }
    }

    override suspend fun delete(): Either<Unit, Error> {
        return userProfileRemoteDataSource.delete()
    }
}