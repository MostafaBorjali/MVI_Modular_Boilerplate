package com.mvi.modular.network.di

import com.mvi.modular.network.core.NetworkConstants.AMMOTEL_BASE_URL
import com.mvi.modular.network.core.NetworkConstants.BASE_URL_DEBUG
import com.mvi.modular.network.core.NetworkConstants.DEFAULT_CONNECTION_TIMEOUT
import com.mvi.modular.network.core.NetworkConstants.DEFAULT_READ_TIMEOUT
import com.mvi.modular.network.core.NetworkConstants.DEFAULT_WRITE_TIMEOUT
import com.mvi.modular.network.core.NetworkConstants.QUALIFIER_AUTH_INTERCEPTOR
import com.mvi.modular.network.core.NetworkConstants.QUALIFIER_LANGUAGE_INTERCEPTOR
import com.mvi.modular.network.core.NetworkConstants.QUALIFIER_NETWORK_INTERCEPTOR
import com.mvi.modular.network.interceptor.NetworkInterceptor
import com.mvi.modular.persist.core.PersistConstants
import com.mvi.modular.persist.service.PersistService
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun networkModule(debug: Boolean = false) = module {

    single<Interceptor>(named(QUALIFIER_NETWORK_INTERCEPTOR)) {
        NetworkInterceptor(
            applicationInfoService = get(),
        )
    }

    single {
        val persistService: PersistService = get()
        val production = persistService.getBoolean(PersistConstants.KEY_SERVER_ENV) ?: false

        val client = if (debug) {
            val logger = HttpLoggingInterceptor()
            logger.setLevel(HttpLoggingInterceptor.Level.BASIC)

            createOkhttpClient()
                .addInterceptor(logger)
                .addInterceptor(interceptor = get(qualifier = named(QUALIFIER_NETWORK_INTERCEPTOR)))
                .addInterceptor(interceptor = get(qualifier = named(QUALIFIER_LANGUAGE_INTERCEPTOR)))
                .addInterceptor(interceptor = get(qualifier = named(QUALIFIER_AUTH_INTERCEPTOR)))
                .build()
        } else {
            createOkhttpClient()
                .addInterceptor(interceptor = get(qualifier = named(QUALIFIER_NETWORK_INTERCEPTOR)))
                .addInterceptor(interceptor = get(qualifier = named(QUALIFIER_LANGUAGE_INTERCEPTOR)))
                .addInterceptor(interceptor = get(qualifier = named(QUALIFIER_AUTH_INTERCEPTOR)))
                .build()
        }

        createRetrofit(
            httpUrl =
            if (debug && !production) {
                BASE_URL_DEBUG.toHttpUrl()
            } else {
                AMMOTEL_BASE_URL.toHttpUrl()
            },
            client = client,
        )
    }
}

private fun createOkhttpClient(): OkHttpClient.Builder {
    return OkHttpClient.Builder()
        .connectTimeout(DEFAULT_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)
}

private fun createRetrofit(
    httpUrl: HttpUrl,
    client: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(httpUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}