package com.mvi.modular.persist.domain.usecase

import com.mvi.modular.persist.domain.repository.PersistRepository


internal interface RetrieveBooleanUseCase {
    /**
     *
     */
    operator fun invoke(key: String): Boolean?
}


internal class DefaultRetrieveBooleanUseCase(
    private val persistRepository: PersistRepository
) : RetrieveBooleanUseCase {

    override fun invoke(key: String): Boolean? {
        return persistRepository.retrieveBoolean(key)
    }
}