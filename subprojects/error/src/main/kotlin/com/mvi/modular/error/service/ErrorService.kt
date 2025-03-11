package com.mvi.modular.error.service

import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.error.domain.model.ErrorInfo


interface ErrorService {

    /**
     * Convert any throwable to application Error
     *
     * @param throwable Target throwable
     * @return Application Error
     */
    fun convert(throwable: Throwable): Error

    /**
     * Convert any application Error to Error information, inorder to show error to user
     *
     * @param error Target Error
     * @return Error information object
     */
    fun convert(error: Error): ErrorInfo
}