package com.mvi.modular.navigation.domain.usecase

import androidx.navigation.NavHostController
import com.mvi.modular.navigation.domain.repository.NavigationRepository


internal interface AddNavigationControllerUseCase {
    /**
     *
     */
    operator fun invoke(id: Long, navHostController: NavHostController)
}


internal class DefaultAddNavigationControllerUseCase(
    private val navigationRepository: NavigationRepository
) : AddNavigationControllerUseCase {

    override fun invoke(id: Long, navHostController: NavHostController) {
        navigationRepository.addNavigationController(id, navHostController)
    }
}