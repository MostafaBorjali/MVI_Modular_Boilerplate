package com.mvi.modular.lang.interceptor

import com.mvi.modular.lang.service.LanguageService
import okhttp3.Interceptor
import okhttp3.Response


internal class LanguageInterceptor(
    private val languageService: LanguageService
) : Interceptor {

    /**
     * Add language code to header of every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("lang", languageService.getCurrentLanguage().languageCode)
        return chain.proceed(request.build())
    }
}