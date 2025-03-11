package com.mvi.modular.persist.domain.usecase

import com.mvi.modular.persist.domain.repository.PersistRepository


internal interface RetrieveLongUseCase {
    /**
     *
     */
    operator fun invoke(key: String): Long?
}


internal class DefaultRetrieveLongUseCase(
    private val persistRepository: PersistRepository
) : RetrieveLongUseCase {

    override fun invoke(key: String): Long? {
        return persistRepository.retrieveLong(key)
    }
}