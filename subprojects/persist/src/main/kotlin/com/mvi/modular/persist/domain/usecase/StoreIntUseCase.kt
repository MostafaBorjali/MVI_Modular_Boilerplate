package com.mvi.modular.persist.domain.usecase

import com.mvi.modular.persist.domain.repository.PersistRepository


internal interface StoreIntUseCase {
    /**
     *
     */
    operator fun invoke(key: String, value: Int): Boolean
}


internal class DefaultStoreIntUseCase(
    private val persistRepository: PersistRepository
) : StoreIntUseCase {

    override fun invoke(key: String, value: Int): Boolean {
        return persistRepository.storeInt(key, value)
    }
}