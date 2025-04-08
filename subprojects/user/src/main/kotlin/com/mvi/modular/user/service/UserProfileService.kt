package com.mvi.modular.user.service

import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.user.domain.model.UserInfo


interface UserProfileService {

    /**
     * Fetch [UserInfo] from server
     *
     * @return [UserInfo] if fetch successfully, [Error] otherwise
     */
    suspend fun fetchUser(): Either<UserInfo, Error>

    /**
     * Get cached [UserInfo]
     *
     * @return [UserInfo] if cached exist, null otherwise
     */
    suspend fun getUser(): UserInfo?

    /**
     * Check if the user is logged in
     *
     * @return true if user exist, false otherwise
     */
    suspend fun isLogin(): Boolean

    /**
     * logout current user, its mean clear all cached [UserInfo] data
     */
    suspend fun logout(): Boolean

    /**
     * Update current user info
     *
     * @param token firebase token
     * @param lang user current lang
     */
    suspend fun updateUserProfile(
        token: String?,
        lang: String?,
    ): Either<UserInfo, Error>

    /**
     * Delete current user account
     */
    suspend fun delete(): Either<Unit, Error>
}