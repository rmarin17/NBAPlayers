package com.rmarin17.nbaplayers.ui.home

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.rmarin17.nbaplayers.ui.navigator.HomeNavigator
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Unit test for HomeViewModel.
 */
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private val homeNavigator: HomeNavigator = mock()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        doNothing().whenever(homeNavigator).navigateToSearchQuery(any())
        homeViewModel = HomeViewModel(homeNavigator)
    }

    @Test
    fun `when navigateToSearchQuery is called, then check navigator navigateToSearchQuery is called`() {
        homeViewModel.navigateToSearchQuery("test")
        verify(homeNavigator, atLeastOnce()).navigateToSearchQuery(any())
    }
}
