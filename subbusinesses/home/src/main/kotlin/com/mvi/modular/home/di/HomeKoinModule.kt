package com.mvi.modular.home.di

import com.mvi.modular.home.screen.esim.ESimScreenViewModel
import com.mvi.modular.home.screen.profile.ProfileScreenViewModel
import com.mvi.modular.home.screen.shop.ShopScreenViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val homeModule = module {

    viewModel {
        ESimScreenViewModel(dispatcher = Dispatchers.IO)
    }

    viewModel {
        ProfileScreenViewModel(dispatcher = Dispatchers.IO)
    }

    viewModel {
        ShopScreenViewModel(dispatcher = Dispatchers.IO)
    }
}