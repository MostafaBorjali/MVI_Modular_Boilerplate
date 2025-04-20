package com.mvi.modular.network.interceptor

import android.os.Build
import com.mvi.modular.integration.service.ApplicationInfoService
import com.mvi.modular.network.core.NetworkConstants.ANDROID
import com.mvi.modular.network.core.NetworkConstants.APP_VERSION_CODE
import com.mvi.modular.network.core.NetworkConstants.APP_VERSION_NAME
import com.mvi.modular.network.core.NetworkConstants.AUTHORIZATION
import com.mvi.modular.network.core.NetworkConstants.BEARER

import com.mvi.modular.network.core.NetworkConstants.OS
import com.mvi.modular.network.core.NetworkConstants.OS_VERSION
import com.mvi.modular.network.core.NetworkConstants.PLATFORM
import com.mvi.modular.network.core.NetworkConstants.TOKEN
import okhttp3.Interceptor
import okhttp3.Response


internal class NetworkInterceptor(
    private val applicationInfoService: ApplicationInfoService,
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val applicationInfo = applicationInfoService.applicationInfo()

        val request = chain.request().newBuilder()
        request
            .addHeader(PLATFORM, ANDROID)
            .addHeader(OS, ANDROID)
            .addHeader(AUTHORIZATION, BEARER + TOKEN)
            .addHeader(OS_VERSION, Build.VERSION.SDK_INT.toString())
            .addHeader(APP_VERSION_CODE, applicationInfo?.versionCode?.toString() ?: "")
            .addHeader(APP_VERSION_NAME, applicationInfo?.versionName ?: "")

        return chain.proceed(request.build())
    }
}