package com.mvi.modular.user.data.datasource.remote

import com.mvi.modular.network.annotation.WithoutAuthentication
import com.mvi.modular.network.marker.NetworkApi
import com.mvi.modular.user.core.UserConstants.GOOGLE_LOGIN
import com.mvi.modular.user.core.UserConstants.REGISTER
import com.mvi.modular.user.core.UserConstants.VERIFY
import com.mvi.modular.user.data.datasource.remote.dto.UserGoogleLoginRequestDto
import com.mvi.modular.user.data.datasource.remote.dto.UserRegisterRequestDto
import com.mvi.modular.user.data.datasource.remote.dto.UserRegisterResponseObjectDto
import com.mvi.modular.user.data.datasource.remote.dto.UserVerifyRequestDto
import com.mvi.modular.user.data.datasource.remote.dto.UserVerifyResponseObjectDto
import retrofit2.http.Body
import retrofit2.http.POST


internal interface UserLoginApi : NetworkApi {

    @POST(REGISTER)
    @WithoutAuthentication
    suspend fun register(
        @Body body: UserRegisterRequestDto
    ): UserRegisterResponseObjectDto

    @POST(VERIFY)
    @WithoutAuthentication
    suspend fun verify(
        @Body body: UserVerifyRequestDto
    ): UserVerifyResponseObjectDto

    @POST(GOOGLE_LOGIN)
    @WithoutAuthentication
    suspend fun googleLogin(
        @Body body: UserGoogleLoginRequestDto
    ): UserVerifyResponseObjectDto
}