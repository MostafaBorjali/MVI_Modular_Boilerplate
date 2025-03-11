package com.mvi.modular.persist.domain.usecase

import com.mvi.modular.persist.domain.repository.PersistRepository


internal interface StoreStringUseCase {
    /**
     *
     */
    operator fun invoke(key: String, value: String): Boolean
}


internal class DefaultStoreStringUseCase(
    private val persistRepository: PersistRepository
) : StoreStringUseCase {

    override fun invoke(key: String, value: String): Boolean {
        return persistRepository.storeString(key, value)
    }
}