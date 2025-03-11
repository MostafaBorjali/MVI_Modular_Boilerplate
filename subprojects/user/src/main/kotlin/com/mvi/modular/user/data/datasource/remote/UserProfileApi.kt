package com.mvi.modular.user.data.datasource.remote

import com.mvi.modular.network.marker.NetworkApi
import com.mvi.modular.user.core.UserConstants.PROFILE
import com.mvi.modular.user.data.datasource.remote.dto.UpdateUserProfileRequestDto
import com.mvi.modular.user.data.datasource.remote.dto.UserDeleteProfileResponseObjectDto
import com.mvi.modular.user.data.datasource.remote.dto.UserProfileResponseObjectDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT


internal interface UserProfileApi : NetworkApi {

    @GET(PROFILE)
    suspend fun profile(): UserProfileResponseObjectDto

    @PUT(PROFILE)
    suspend fun update(
        @Body body: UpdateUserProfileRequestDto
    ): UserProfileResponseObjectDto

    @DELETE(PROFILE)
    suspend fun delete(): UserDeleteProfileResponseObjectDto
}