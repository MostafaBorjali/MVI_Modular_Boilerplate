package com.mvi.modular.navigation.domain.repository

import androidx.navigation.NavHostController


internal interface NavigationRepository {

    /**
     *
     */
    fun addNavigationController(id: Long, navHostController: NavHostController)

    /**
     *
     */
    fun getNavigationController(id: Long): NavHostController?
}