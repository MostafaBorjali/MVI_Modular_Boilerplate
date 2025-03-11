package com.mvi.modular.persist.domain.usecase

import com.mvi.modular.persist.domain.repository.PersistRepository


internal interface RetrieveIntUseCase {
    /**
     *
     */
    operator fun invoke(key: String): Int?
}


internal class DefaultRetrieveIntUseCase(
    private val persistRepository: PersistRepository
) : RetrieveIntUseCase {

    override fun invoke(key: String): Int? {
        return persistRepository.retrieveInt(key)
    }
}