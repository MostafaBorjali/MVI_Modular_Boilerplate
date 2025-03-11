package com.mvi.modular.error.domain.model


data class ErrorInfo(
    val message: String,
    val code: Int = 0,
    val cancelable: Boolean = true,
    val retryable: Boolean = true,
    val reLogin: Boolean = false
)
