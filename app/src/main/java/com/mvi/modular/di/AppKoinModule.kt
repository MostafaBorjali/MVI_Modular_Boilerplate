package com.mvi.modular.di


import com.mvi.modular.resolver.DefaultActivityResolver
import com.mvi.modular.integration.resolver.ActivityResolver
import org.koin.dsl.module


val appModule = module {

    single<ActivityResolver> {
        DefaultActivityResolver()
    }
}