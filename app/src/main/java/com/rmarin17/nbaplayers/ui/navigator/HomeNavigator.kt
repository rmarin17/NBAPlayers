package com.rmarin17.nbaplayers.ui.navigator

import com.rmarin17.nbaplayers.ui.models.PlayerUiModel

/**
 * Interface of HomeNavigator, to handle the navigation.
 */
interface HomeNavigator {

    fun navigateToSearchQuery(query: String)
    fun navigateToPlayerDetail(player: PlayerUiModel)
}
