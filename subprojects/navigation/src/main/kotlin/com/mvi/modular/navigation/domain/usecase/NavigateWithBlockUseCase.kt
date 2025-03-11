package com.mvi.modular.navigation.domain.usecase

import androidx.navigation.NavHostController
import com.mvi.modular.navigation.domain.repository.NavigationRepository


internal interface NavigateWithBlockUseCase {
    /**
     *
     */
    operator fun invoke(id: Long, block: (NavHostController) -> Unit)
}


internal class DefaultNavigateWithBlockUseCase(
    private val navigationRepository: NavigationRepository
) : NavigateWithBlockUseCase {

    override fun invoke(id: Long, block: (NavHostController) -> Unit) {
        val navHostController = navigationRepository.getNavigationController(id)
            ?: throw IllegalStateException("Navigation controller with id $id not found.")
        //
        // navigate
        //
        block(navHostController)
    }
}