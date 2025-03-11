package com.mvi.modular.auth.domain.model


data class AuthToken(
    val value: String,
    val expireAt: Long,
)