package com.rmarin17.nbaplayers.ui.search

import com.rmarin17.nbaplayers.ui.models.PlayerUiModel

/**
 * Class to handle the different states of the SearchFragment.
 */
sealed class PlayerSearchState {
    data class Loading(val loadedAction: () -> Unit) : PlayerSearchState()
    object PlayerResultFailure : PlayerSearchState()
    data class PlayerResultSuccess(val players: List<PlayerUiModel>) : PlayerSearchState()
}
