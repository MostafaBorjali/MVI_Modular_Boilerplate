package com.mvi.modular.user.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName


internal data class UserProfileResponseObjectDto(
    @SerializedName("response")
    val response: UserInfoDto,
    @SerializedName("status")
    val status: Int,
)

internal data class UserInfoDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("status")
    val status: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("lang")
    val lang: String,
)


//
// update
//

internal data class UpdateUserProfileRequestDto(
    @SerializedName("fcm")
    val fcmToken: String?,
    @SerializedName("lang")
    val lang: String?,
)

//
// delete
//

internal data class UserDeleteProfileResponseObjectDto(
    @SerializedName("status")
    val status: Int,
)