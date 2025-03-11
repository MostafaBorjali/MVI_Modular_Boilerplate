package com.mvi.modular.user.domain.model


data class UserInfo(
    val id: Long,
    val status: Int,
    val email: String,
    val avatar: String?,
    val name: String?,
    val lang: String,
)