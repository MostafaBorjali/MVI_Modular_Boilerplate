package com.mvi.modular.lang.service

import androidx.annotation.StringRes


interface LocalizedStringService {

    /**
     * Get string of target resource id in application language
     *
     * @param resourceId Target resources
     * @return localized string
     */
    fun stringResource(@StringRes resourceId: Int): String

    /**
     * Get string of target resource id in application language with input params
     *
     * @param resourceId Target resources
     * @param args Target params
     * @return localized string
     */
    fun stringResource(@StringRes resourceId: Int, vararg args: Any): String
}