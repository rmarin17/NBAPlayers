package com.rmarin17.nbaplayers.domain.interactors

import com.rmarin17.nbaplayers.data.network.PlayerServices
import com.rmarin17.nbaplayers.ui.models.PlayerUiModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Implementation of FetchPlayersInteractorImpl
 */
class FetchPlayersInteractorImpl @Inject constructor(private val productsService: PlayerServices) : FetchPlayersInteractor {

    override fun getAllPlayers(): Single<List<PlayerUiModel>> {
        return productsService.getPlayers()
            .firstElement()
            .map { listPlayer ->
                listPlayer.map { it.transformToPlayerUiModel() }
            }
            .toSingle()
    }

    override fun getPlayersByQuery(query: String): Single<List<PlayerUiModel>> {
        return productsService.getPlayersByQuery(query)
            .firstElement()
            .map { listPlayer ->
                listPlayer.map { it.transformToPlayerUiModel() }
            }
            .toSingle()
    }
}
