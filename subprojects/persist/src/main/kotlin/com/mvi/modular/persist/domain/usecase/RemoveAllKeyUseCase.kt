package com.mvi.modular.persist.domain.usecase

import com.mvi.modular.persist.domain.repository.PersistRepository


internal interface RemoveAllKeyUseCase {
    /**
     *
     */
    operator fun invoke()
}


internal class DefaultRemoveAllKeyUseCase(
    private val persistRepository: PersistRepository
) : RemoveAllKeyUseCase {

    override fun invoke() {
        persistRepository.removeAllKey()
    }
}