package com.mvi.modular.persist.service

import com.mvi.modular.persist.domain.usecase.CheckSpecificKeyExistUseCase
import com.mvi.modular.persist.domain.usecase.RemoveAllKeyUseCase
import com.mvi.modular.persist.domain.usecase.RemoveSpecificKeyUseCase
import com.mvi.modular.persist.domain.usecase.RetrieveBooleanUseCase
import com.mvi.modular.persist.domain.usecase.RetrieveIntUseCase
import com.mvi.modular.persist.domain.usecase.RetrieveLongUseCase
import com.mvi.modular.persist.domain.usecase.RetrieveStringUseCase
import com.mvi.modular.persist.domain.usecase.StoreBooleanUseCase
import com.mvi.modular.persist.domain.usecase.StoreIntUseCase
import com.mvi.modular.persist.domain.usecase.StoreLongUseCase
import com.mvi.modular.persist.domain.usecase.StoreStringUseCase


internal class DefaultPersistService(
    private val storeStringUseCase: StoreStringUseCase,
    private val retrieveStringUseCase: RetrieveStringUseCase,
    private val storeBooleanUseCase: StoreBooleanUseCase,
    private val retrieveBooleanUseCase: RetrieveBooleanUseCase,
    private val storeLongUseCase: StoreLongUseCase,
    private val retrieveLongUseCase: RetrieveLongUseCase,
    private val storeIntUseCase: StoreIntUseCase,
    private val retrieveIntUseCase: RetrieveIntUseCase,
    private val checkSpecificKeyExistUseCase: CheckSpecificKeyExistUseCase,
    private val removeSpecificKeyUseCase: RemoveSpecificKeyUseCase,
    private val removeAllKeyUseCase: RemoveAllKeyUseCase
) : PersistService {


    override fun putString(key: String, value: String): Boolean {
        return storeStringUseCase(key, value)
    }

    override fun getString(key: String): String? {
        return retrieveStringUseCase(key)
    }

    override fun putBoolean(key: String, value: Boolean): Boolean {
        return storeBooleanUseCase(key, value)
    }

    override fun getBoolean(key: String): Boolean? {
        return retrieveBooleanUseCase(key)
    }

    override fun putLong(key: String, value: Long): Boolean {
        return storeLongUseCase(key, value)
    }

    override fun getLong(key: String): Long? {
        return retrieveLongUseCase(key)
    }

    override fun putInt(key: String, value: Int): Boolean {
        return storeIntUseCase(key, value)
    }

    override fun getInt(key: String): Int? {
        return retrieveIntUseCase(key)
    }

    override fun contains(key: String): Boolean {
        return checkSpecificKeyExistUseCase(key)
    }

    override fun removeKey(key: String): Boolean {
        return removeSpecificKeyUseCase(key)
    }

    override fun removeAll() {
        removeAllKeyUseCase()
    }
}