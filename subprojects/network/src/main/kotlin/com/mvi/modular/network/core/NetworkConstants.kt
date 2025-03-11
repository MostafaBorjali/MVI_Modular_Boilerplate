package com.mvi.modular.network.core


object NetworkConstants {

    internal const val AMMOTEL_BASE_URL = "https://mvi.modular.com"
    internal const val BASE_URL_DEBUG = "https://mvi-dev.modular.com"

    internal const val DEFAULT_CONNECTION_TIMEOUT = 30L
    internal const val DEFAULT_READ_TIMEOUT = 60L
    internal const val DEFAULT_WRITE_TIMEOUT = 60L

    /*
     * Interceptors qualifier key
     */
    const val QUALIFIER_LANGUAGE_INTERCEPTOR = "lang-interceptor"
    const val QUALIFIER_AUTH_INTERCEPTOR = "auth-interceptor"
    const val QUALIFIER_NETWORK_INTERCEPTOR = "network-interceptor"
}