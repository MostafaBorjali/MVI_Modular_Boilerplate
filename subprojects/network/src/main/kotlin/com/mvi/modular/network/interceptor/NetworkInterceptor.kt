package com.mvi.modular.network.interceptor

import android.os.Build
import com.mvi.modular.integration.service.ApplicationInfoService
import okhttp3.Interceptor
import okhttp3.Response


internal class NetworkInterceptor(
    private val applicationInfoService: ApplicationInfoService,
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val applicationInfo = applicationInfoService.applicationInfo()

        val request = chain.request().newBuilder()
        request
            .addHeader("platform", "Android")
            .addHeader("os", "Android")
            .addHeader("os_version", Build.VERSION.SDK_INT.toString())
            .addHeader("app_version_code", applicationInfo?.versionCode?.toString() ?: "")
            .addHeader("app_version_name", applicationInfo?.versionName ?: "")

        return chain.proceed(request.build())
    }
}