package com.mvi.modular.navigation.data.repository

import androidx.navigation.NavHostController
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mvi.modular.navigation.domain.repository.NavigationRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


@SmallTest
class NavigationRepositoryTest {

    private lateinit var navigationRepository: NavigationRepository

    @Before
    fun setUp() {
        navigationRepository = DefaultNavigationRepository()
    }


    @Test
    fun test_add_navigation_and_get_it_with_same_id_must_return_same_navigation() {
        val navHost = Mockito.mock(NavHostController::class.java)
        navigationRepository.addNavigationController(1, navHost)

        val result = navigationRepository.getNavigationController(1)
        assertThat(result).isEqualTo(navHost)
    }

    @Test
    fun test_get_navigation_with_some_id_that_not_exist_must_return_null() {
        val result = navigationRepository.getNavigationController(1)
        assertThat(result).isNull()
    }
}