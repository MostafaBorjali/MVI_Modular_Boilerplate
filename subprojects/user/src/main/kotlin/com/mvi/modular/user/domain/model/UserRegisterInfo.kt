package com.mvi.modular.user.domain.model


data class UserRegisterInfo(
    val resendDuration: Int,
    val verificationCodeLength: Int
)
