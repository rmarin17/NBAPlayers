package com.rmarin17.nbaplayers.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rmarin17.nbaplayers.common.BaseViewModel
import com.rmarin17.nbaplayers.common.ext.applyIOSubscribeMainThread
import com.rmarin17.nbaplayers.common.logger.Logger
import com.rmarin17.nbaplayers.domain.interactors.FetchPlayersInteractor
import com.rmarin17.nbaplayers.ui.models.PlayerUiModel
import com.rmarin17.nbaplayers.ui.navigator.HomeNavigator
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

/**
 * Class to acquire and keep the information that is necessary to show in fragment search.
 */
class PlayersSearchViewModel @Inject constructor(
    private val fetchPlayersInteractor: FetchPlayersInteractor,
    private val logger: Logger,
    private val navigator: HomeNavigator
) : BaseViewModel() {

    private val _playersSearchState = MutableLiveData<PlayerSearchState>()
    val playersSearchState: LiveData<PlayerSearchState> get() = _playersSearchState

    fun getAllPlayers() {
        _playersSearchState.value = PlayerSearchState.Loading {
            addDisposable(executeFetchPlayers())
        }
    }

    fun searchPlayersByQuery(query: String) {
        _playersSearchState.value = PlayerSearchState.Loading {
            addDisposable(executeFetchPlayersByQuery(query))
        }
    }

    fun navigateToPlayerDetail(player: PlayerUiModel) {
        navigator.navigateToPlayerDetail(player)
    }

    private fun executeFetchPlayers(): Disposable {
        return fetchPlayersInteractor.getAllPlayers()
            .applyIOSubscribeMainThread()
            .subscribe(
                { players ->
                    _playersSearchState.value = PlayerSearchState.PlayerResultSuccess(players)
                }, {
                    _playersSearchState.value = PlayerSearchState.PlayerResultFailure
                    logger.logMessage(this.javaClass.name, "Error on executeFetchPlayers due to: ${it.localizedMessage}", Logger.Level.ERROR)
                }
            )
    }

    private fun executeFetchPlayersByQuery(query: String): Disposable {
        return fetchPlayersInteractor.getPlayersByQuery(query)
            .applyIOSubscribeMainThread()
            .subscribe(
                { players ->
                    _playersSearchState.value = PlayerSearchState.PlayerResultSuccess(players)
                }, {
                    _playersSearchState.value = PlayerSearchState.PlayerResultFailure
                    logger.logMessage(this.javaClass.name, "Error on executeFetchPlayersByQuery due to: ${it.localizedMessage}", Logger.Level.ERROR)
                }
            )
    }
}
