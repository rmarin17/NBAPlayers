package com.rmarin17.nbaplayers.domain.interactors

import com.rmarin17.nbaplayers.ui.models.PlayerUiModel
import io.reactivex.rxjava3.core.Single

/**
 * Interface to handle all the methods related player interactor.
 */
interface FetchPlayersInteractor {

    fun getAllPlayers(): Single<List<PlayerUiModel>>
    fun getPlayersByQuery(query: String): Single<List<PlayerUiModel>>
}
