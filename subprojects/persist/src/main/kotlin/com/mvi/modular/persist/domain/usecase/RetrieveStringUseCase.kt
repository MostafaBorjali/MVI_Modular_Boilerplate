package com.mvi.modular.persist.domain.usecase

import com.mvi.modular.persist.domain.repository.PersistRepository


internal interface RetrieveStringUseCase {
    /**
     *
     */
    operator fun invoke(key: String): String?
}


internal class DefaultRetrieveStringUseCase(
    private val persistRepository: PersistRepository
) : RetrieveStringUseCase {

    override fun invoke(key: String): String? {
        return persistRepository.retrieveString(key)
    }
}
