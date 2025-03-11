package com.mvi.modular.navigation.domain.usecase

import com.mvi.modular.navigation.domain.model.Destination
import com.mvi.modular.navigation.domain.repository.NavigationRepository


internal interface PopBackstackUseCase {
    /**
     *
     */
    operator fun invoke(
        id: Long,
        destination: Destination?,
        inclusive: Boolean
    )
}


internal class DefaultPopBackstackUseCase(
    private val navigationRepository: NavigationRepository
) : PopBackstackUseCase {

    override fun invoke(
        id: Long,
        destination: Destination?,
        inclusive: Boolean
    ) {
        val navHostController = navigationRepository.getNavigationController(id)
            ?: throw IllegalStateException("Navigation controller with id $id not found.")
        //
        // pop
        //
        if (destination == null) {
            navHostController.popBackStack()
        } else {
            navHostController.popBackStack(
                route = destination.route,
                inclusive = inclusive
            )
        }
    }
}