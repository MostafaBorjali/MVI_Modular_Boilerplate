package com.mvi.modular.user.data.mapper

import com.google.gson.Gson
import com.mvi.modular.user.data.datasource.remote.dto.UserInfoDto
import com.mvi.modular.user.data.datasource.remote.dto.UserRegisterResponseDto
import com.mvi.modular.user.domain.model.UserInfo
import com.mvi.modular.user.domain.model.UserRegisterInfo


/**
 *
 */
internal fun UserRegisterResponseDto.toUserRegisterInfo(): UserRegisterInfo {
    return UserRegisterInfo(
        resendDuration = resendDuration,
        verificationCodeLength = length
    )
}

/**
 *
 */
internal fun UserInfoDto.toJson(): String {
    return Gson().toJson(this, UserInfoDto::class.java)
}

/**
 *
 */
internal fun String.toUserInfoDto(): UserInfoDto? {
    return try {
        Gson().fromJson(this, UserInfoDto::class.java)
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }
}

/**
 *
 */
internal fun UserInfoDto.toUserInfo(): UserInfo {
    return UserInfo(
        id = id,
        status = status,
        email = email,
        avatar = avatar,
        name = name,
        lang = lang
    )
}