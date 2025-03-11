package com.mvi.modular.user.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

//
// Requests
//

internal data class UserRegisterRequestDto(
    @SerializedName("email")
    val email: String,
)

internal data class UserVerifyRequestDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("code")
    val code: String,
)

internal data class UserGoogleLoginRequestDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("token")
    val token: String,
)


//
// Responses
//

internal data class UserRegisterResponseObjectDto(
    @SerializedName("response")
    val response: UserRegisterResponseDto,
    @SerializedName("status")
    val status: Int,
)

internal data class UserRegisterResponseDto(
    @SerializedName("resend_duration")
    val resendDuration: Int,
    @SerializedName("length")
    val length: Int,
)

internal data class UserVerifyResponseObjectDto(
    @SerializedName("response")
    val response: UserVerifyResponseDto,
    @SerializedName("status")
    val status: Int,
)

internal data class UserVerifyResponseDto(
    @SerializedName("token")
    val token: String,
    @SerializedName("expired_at")
    val expiredAt: Long,
    @SerializedName("user")
    val user: UserInfoDto,
)