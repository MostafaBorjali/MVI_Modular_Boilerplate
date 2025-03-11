package com.mvi.modular.user.data.datasource.remote

import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.user.data.datasource.remote.dto.UserInfoDto


internal interface UserProfileRemoteDataSource {

    /**
     * Fetch user profile
     */
    suspend fun profile(): Either<UserInfoDto, Error>

    /**
     * Update current user
     *
     * @param token firebase token
     * @param lang user current lang
     */
    suspend fun update(token: String?, lang: String?): Either<UserInfoDto, Error>


    /**
     * Delete current user profile
     */
    suspend fun delete(): Either<Unit, Error>
}