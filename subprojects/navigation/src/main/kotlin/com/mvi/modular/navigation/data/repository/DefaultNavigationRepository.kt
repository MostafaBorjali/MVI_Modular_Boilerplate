package com.mvi.modular.navigation.data.repository

import androidx.navigation.NavHostController
import com.mvi.modular.navigation.domain.repository.NavigationRepository


internal class DefaultNavigationRepository : NavigationRepository {

    /**
     *
     */
    private val navHostControllersMap = mutableMapOf<Long, NavHostController>()


    override fun addNavigationController(id: Long, navHostController: NavHostController) {
        navHostControllersMap[id] = navHostController
    }

    override fun getNavigationController(id: Long): NavHostController? {
        return navHostControllersMap[id]
    }
}