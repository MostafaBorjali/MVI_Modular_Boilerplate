package com.mvi.modular.persist.domain.usecase

import com.mvi.modular.persist.domain.repository.PersistRepository


internal interface RemoveSpecificKeyUseCase {
    /**
     *
     */
    operator fun invoke(key: String): Boolean
}


internal class DefaultRemoveSpecificKeyUseCase(
    private val persistRepository: PersistRepository
) : RemoveSpecificKeyUseCase {

    override fun invoke(key: String): Boolean {
        return persistRepository.removeSpecificKey(key)
    }
}