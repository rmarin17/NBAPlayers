package com.rmarin17.nbaplayers.ui.home

import com.rmarin17.nbaplayers.common.BaseViewModel
import com.rmarin17.nbaplayers.ui.navigator.HomeNavigator
import javax.inject.Inject

/**
 * Class to acquire and keep the information that is necessary to show in home activity.
 */
class HomeViewModel @Inject constructor(private val navigator: HomeNavigator) : BaseViewModel() {

    fun navigateToSearchQuery(query: String) {
        navigator.navigateToSearchQuery(query)
    }
}
