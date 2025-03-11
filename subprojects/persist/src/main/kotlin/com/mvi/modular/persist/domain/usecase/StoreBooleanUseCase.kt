package com.mvi.modular.persist.domain.usecase

import com.mvi.modular.persist.domain.repository.PersistRepository


internal interface StoreBooleanUseCase {
    /**
     *
     */
    operator fun invoke(key: String, value: Boolean): Boolean
}


internal class DefaultStoreBooleanUseCase(
    private val persistRepository: PersistRepository
) : StoreBooleanUseCase {

    override fun invoke(key: String, value: Boolean): Boolean {
        return persistRepository.storeBoolean(key, value)
    }
}