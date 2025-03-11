package com.mvi.modular.barcode.di

import com.mvi.modular.barcode.service.BarcodeService
import com.mvi.modular.barcode.service.DefaultBarcodeService
import org.koin.dsl.module


val barcodeModule = module {

    single<BarcodeService> { DefaultBarcodeService() }
}