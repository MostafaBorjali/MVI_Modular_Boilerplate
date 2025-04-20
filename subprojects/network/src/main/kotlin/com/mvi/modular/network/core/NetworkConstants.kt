package com.mvi.modular.network.core


object NetworkConstants {

    internal const val BASE_URL_PRODUCTION = "https://api.themoviedb.org"
    internal const val BASE_URL_DEBUG = "https://api.themoviedb.org"

    internal const val DEFAULT_CONNECTION_TIMEOUT = 30L
    internal const val DEFAULT_READ_TIMEOUT = 60L
    internal const val DEFAULT_WRITE_TIMEOUT = 60L

    /*
     * Interceptors qualifier key
     */
    const val QUALIFIER_LANGUAGE_INTERCEPTOR = "lang-interceptor"
    const val QUALIFIER_AUTH_INTERCEPTOR = "auth-interceptor"
    const val QUALIFIER_NETWORK_INTERCEPTOR = "network-interceptor"

    const val APP_VERSION_NAME = "app_version_name"
    const val APP_VERSION_CODE = "app_version_code"
    const val OS_VERSION = "os_version"
    const val PLATFORM = "platform"
    const val OS = "os"
    const val ANDROID = "android"
    const val BEARER = "Bearer "
    const val AUTHORIZATION = "Authorization"
    const val TOKEN =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyZjdkNzFjOTgwYTA1N2Q2YTIwOWJkYzVkZGQ0NzY4ZCIsInN1YiI6IjY2MjFmZTdjZTRjOWViMDE2M2Y1Y2Q4YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.-i6CsB5ruwkFML8s5PlO59uK9P54VIuY8lby9rjOZus"
}
