package com.rmarin17.nbaplayers.domain.interactors

import com.rmarin17.nbaplayers.data.network.PlayerServices
import com.rmarin17.nbaplayers.ui.models.PlayerUiModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Implementation of FetchPlayersInteractorImpl
 */
class FetchPlayersInteractorImpl @Inject constructor(private val playersService: PlayerServices) : FetchPlayersInteractor {

    override fun getAllPlayers(): Single<List<PlayerUiModel>> {
        return playersService.getPlayers()
            .firstElement()
            .map { it.transformToListOfPlayersUiModel() }
            .toSingle()
    }

    override fun getPlayersByQuery(query: String): Single<List<PlayerUiModel>> {
        return playersService.getPlayersByQuery(query)
            .firstElement()
            .map { it.transformToListOfPlayersUiModel() }
            .toSingle()
    }
}
