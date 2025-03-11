package com.mvi.modular.persist.domain.usecase

import com.mvi.modular.persist.domain.repository.PersistRepository


internal interface StoreLongUseCase {
    /**
     *
     */
    operator fun invoke(key: String, value: Long): Boolean
}


internal class DefaultStoreLongUseCase(
    private val persistRepository: PersistRepository
) : StoreLongUseCase {

    override fun invoke(key: String, value: Long): Boolean {
        return persistRepository.storeLong(key, value)
    }
}