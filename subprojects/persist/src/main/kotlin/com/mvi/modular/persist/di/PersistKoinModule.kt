package com.mvi.modular.persist.di

import android.content.Context
import android.content.SharedPreferences
import com.mvi.modular.persist.data.repository.DefaultPersistRepository
import com.mvi.modular.persist.domain.usecase.DefaultCheckSpecificKeyExistUseCase
import com.mvi.modular.persist.domain.usecase.DefaultRemoveAllKeyUseCase
import com.mvi.modular.persist.domain.usecase.DefaultRemoveSpecificKeyUseCase
import com.mvi.modular.persist.domain.usecase.DefaultRetrieveBooleanUseCase
import com.mvi.modular.persist.domain.usecase.DefaultRetrieveIntUseCase
import com.mvi.modular.persist.domain.usecase.DefaultRetrieveLongUseCase
import com.mvi.modular.persist.domain.usecase.DefaultRetrieveStringUseCase
import com.mvi.modular.persist.domain.usecase.DefaultStoreBooleanUseCase
import com.mvi.modular.persist.domain.usecase.DefaultStoreIntUseCase
import com.mvi.modular.persist.domain.usecase.DefaultStoreLongUseCase
import com.mvi.modular.persist.domain.usecase.DefaultStoreStringUseCase
import com.mvi.modular.persist.service.DefaultPersistService
import com.mvi.modular.persist.service.PersistService
import org.koin.dsl.module


fun persistModule(test: Boolean = false) = module {

    single<PersistService> {
        fun provideSharedPreference(context: Context): SharedPreferences {
            val name = if (test) {
                "${context.packageName}_persist_preferences_test"
            } else {
                "${context.packageName}_persist_preferences"
            }
            return context.getSharedPreferences(name, Context.MODE_PRIVATE)
        }

        val repository = DefaultPersistRepository(
            provideSharedPreference(get())
        )

        val checkSpecificKeyExistUseCase = DefaultCheckSpecificKeyExistUseCase(repository)
        val removeAllKeyUseCase = DefaultRemoveAllKeyUseCase(repository)
        val removeSpecificKeyUseCase = DefaultRemoveSpecificKeyUseCase(repository)
        val retrieveBooleanUseCase = DefaultRetrieveBooleanUseCase(repository)
        val retrieveIntUseCase = DefaultRetrieveIntUseCase(repository)
        val retrieveLongUseCase = DefaultRetrieveLongUseCase(repository)
        val retrieveStringUseCase = DefaultRetrieveStringUseCase(repository)
        val storeBooleanUseCase = DefaultStoreBooleanUseCase(repository)
        val storeIntUseCase = DefaultStoreIntUseCase(repository)
        val storeLongUseCase = DefaultStoreLongUseCase(repository)
        val storeStringUseCase = DefaultStoreStringUseCase(repository)

        DefaultPersistService(
            storeStringUseCase = storeStringUseCase,
            retrieveStringUseCase = retrieveStringUseCase,
            storeBooleanUseCase = storeBooleanUseCase,
            retrieveBooleanUseCase = retrieveBooleanUseCase,
            storeLongUseCase = storeLongUseCase,
            retrieveLongUseCase = retrieveLongUseCase,
            storeIntUseCase = storeIntUseCase,
            retrieveIntUseCase = retrieveIntUseCase,
            checkSpecificKeyExistUseCase = checkSpecificKeyExistUseCase,
            removeSpecificKeyUseCase = removeSpecificKeyUseCase,
            removeAllKeyUseCase = removeAllKeyUseCase
        )
    }
}