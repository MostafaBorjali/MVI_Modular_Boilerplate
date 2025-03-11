package com.mvi.modular.intro.di

import com.mvi.modular.intro.screen.IntroScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val introModule = module {


    viewModel {
        IntroScreenViewModel(
            languageService = get(),
            persistService = get(),
        )
    }
}