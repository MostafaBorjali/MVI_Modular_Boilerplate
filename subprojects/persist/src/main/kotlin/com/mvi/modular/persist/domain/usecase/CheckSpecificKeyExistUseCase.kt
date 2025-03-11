package com.mvi.modular.persist.domain.usecase

import com.mvi.modular.persist.domain.repository.PersistRepository


internal interface CheckSpecificKeyExistUseCase {
    /**
     *
     */
    operator fun invoke(key: String): Boolean
}


internal class DefaultCheckSpecificKeyExistUseCase(
    private val persistRepository: PersistRepository
) : CheckSpecificKeyExistUseCase {

    override fun invoke(key: String): Boolean {
        return persistRepository.containsKey(key)
    }
}