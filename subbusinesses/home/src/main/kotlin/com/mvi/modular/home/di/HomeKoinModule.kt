package com.mvi.modular.home.di

import com.mvi.modular.home.screen.explore.ExploreScreenViewModel
import com.mvi.modular.home.screen.home.HomeScreenViewModel
import com.mvi.modular.home.screen.profile.ProfileScreenViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val homeModule = module {

    viewModel {
        HomeScreenViewModel(
            moviesService = get(),
            languageService = get(),
            errorService = get(),
            dispatcher = Dispatchers.IO
        )
    }

    viewModel {
        ProfileScreenViewModel(dispatcher = Dispatchers.IO)
    }

    viewModel {
        ExploreScreenViewModel(dispatcher = Dispatchers.IO)
    }
}