package com.mvi.modular.error.domain.mapper

import com.mvi.modular.error.domain.model.BusinessError


internal interface BusinessErrorConverter {

    /**
     * Convert error response body to business error
     *
     * @param errorBody Target error json
     * @return Business error or null if get error in parse json
     */
    fun convert(errorBody: String): BusinessError?
}