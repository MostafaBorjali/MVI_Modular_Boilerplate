package com.mvi.modular.error.domain.provider

import com.mvi.modular.error.domain.model.Error


internal interface ErrorMessageProvider {

    /**
     * Provide message for target error
     *
     * @param error Target error
     * @return message of error
     */
    fun message(error: Error): String
}