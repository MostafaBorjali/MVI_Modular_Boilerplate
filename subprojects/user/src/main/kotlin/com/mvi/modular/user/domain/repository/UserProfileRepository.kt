package com.mvi.modular.user.domain.repository

import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.user.domain.model.UserInfo


internal interface UserProfileRepository {

    /**
     * Fetch user online
     */
    suspend fun fetchUser(): Either<UserInfo, Error>

    /**
     * Get cached user
     */
    suspend fun getUser(): UserInfo?

    /**
     * Logout current user
     */
    suspend fun logoutUser(): Boolean

    /**
     * Update current user profile
     *
     * @param token firebase token
     * @param lang user current lang
     */
    suspend fun updateUserProfile(token: String?, lang: String?): Either<UserInfo, Error>

    /**
     * Delete current user profile
     */
    suspend fun delete(): Either<Unit, Error>
}