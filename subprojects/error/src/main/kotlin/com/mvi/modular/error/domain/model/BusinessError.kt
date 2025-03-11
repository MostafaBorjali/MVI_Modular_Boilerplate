package com.mvi.modular.error.domain.model


internal data class BusinessError(
    val internalCode: Int,
    val message: String? = null,
    val detail: String? = null
)
