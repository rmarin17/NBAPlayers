package com.rmarin17.nbaplayers.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.rmarin17.nbaplayers.andTrigger
import com.rmarin17.nbaplayers.common.logger.Logger
import com.rmarin17.nbaplayers.domain.interactors.FetchPlayersInteractor
import com.rmarin17.nbaplayers.test.RxAndroidTrampolineRule
import com.rmarin17.nbaplayers.test.RxTestSchedulerRule
import com.rmarin17.nbaplayers.ui.models.PlayerUiModel
import com.rmarin17.nbaplayers.ui.models.TeamUiModel
import com.rmarin17.nbaplayers.ui.navigator.HomeNavigator
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Unit test of PlayersSearchViewModelTest.
 */
@RunWith(MockitoJUnitRunner::class)
class PlayersSearchViewModelTest {

    private val fetchPlayersInteractor: FetchPlayersInteractor = mock()
    private val logger: Logger = mock()
    private val navigator: HomeNavigator = mock()

    private lateinit var viewModel: PlayersSearchViewModel

    @get:Rule
    val androidTrampolineRule = RxAndroidTrampolineRule()

    @get:Rule
    val testScheduler = RxTestSchedulerRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val scheduler = testScheduler.scheduler

    private val PLAYER_1 = mockPlayer(1)
    private val PLAYER_2 = mockPlayer(2)

    @Before
    fun setUp() {
        viewModel = PlayersSearchViewModel(
            fetchPlayersInteractor,
            logger,
            navigator
        )
    }

    @Test
    fun `when getAllPlayers is called, with view in null state, should change playersSearchState to Loading`() {
        { viewModel.getAllPlayers() } andTrigger scheduler
        TestCase.assertTrue(viewModel.playersSearchState.value is PlayerSearchState.Loading)
    }

    @Test
    fun `when getAllPlayers is called without errors, with view loading state, should change playersSearchState to PlayerResultSuccess`() {
        whenever(fetchPlayersInteractor.getAllPlayers()).thenReturn(Single.just(mockPlayers(false, PLAYER_1, PLAYER_2)))
        viewModel.advanceToFetchAllPlayers(scheduler)
        TestCase.assertTrue(viewModel.playersSearchState.value is PlayerSearchState.PlayerResultSuccess)
        val firstValue = (viewModel.playersSearchState.value as PlayerSearchState.PlayerResultSuccess).players.first()
        TestCase.assertTrue(firstValue.id == PLAYER_1.id)
        TestCase.assertTrue(firstValue.name == PLAYER_1.name)
        TestCase.assertTrue(firstValue.image == PLAYER_1.image)
        TestCase.assertTrue(firstValue.position == PLAYER_1.position)
        TestCase.assertTrue(firstValue.weight == PLAYER_1.weight)
        TestCase.assertTrue(firstValue.height == PLAYER_1.height)
    }

    @Test
    fun `when getAllPlayers is called, result is empty list, with view loading state, should change playersSearchState to PlayerResultSuccess`() {
        whenever(fetchPlayersInteractor.getAllPlayers()).thenReturn(Single.just(mockPlayers(true)))
        viewModel.advanceToFetchAllPlayers(scheduler)
        TestCase.assertTrue(viewModel.playersSearchState.value is PlayerSearchState.PlayerResultSuccess)
        val players = (viewModel.playersSearchState.value as PlayerSearchState.PlayerResultSuccess).players
        TestCase.assertTrue(players.isEmpty())
    }

    @Test
    fun `when getAllPlayers is called with errors, with view loading state, should change playersSearchState to PlayerResultFailure`() {
        whenever(fetchPlayersInteractor.getAllPlayers()).thenReturn(Single.error(Throwable("test error")))
        viewModel.advanceToFetchAllPlayers(scheduler)
        TestCase.assertTrue(viewModel.playersSearchState.value is PlayerSearchState.PlayerResultFailure)
    }

    @Test
    fun `when searchPlayersByQuery is called, with view null state, should change playersSearchState to Loading`() {
        { viewModel.searchPlayersByQuery("test") } andTrigger scheduler
        TestCase.assertTrue(viewModel.playersSearchState.value is PlayerSearchState.Loading)
    }

    @Test
    fun `when getPlayersByQuery is called without errors, with view loading state, should change playersSearchState to PlayerResultSuccess`() {
        whenever(fetchPlayersInteractor.getPlayersByQuery(any())).thenReturn(Single.just(mockPlayers(false, PLAYER_1, PLAYER_2)))
        viewModel.advanceToSearchPlayers(scheduler)
        val firstValue = (viewModel.playersSearchState.value as PlayerSearchState.PlayerResultSuccess).players.first()
        TestCase.assertTrue(firstValue.id == PLAYER_1.id)
        TestCase.assertTrue(firstValue.name == PLAYER_1.name)
        TestCase.assertTrue(firstValue.image == PLAYER_1.image)
        TestCase.assertTrue(firstValue.position == PLAYER_1.position)
        TestCase.assertTrue(firstValue.weight == PLAYER_1.weight)
        TestCase.assertTrue(firstValue.height == PLAYER_1.height)
    }

    @Test
    fun `when getPlayersByQuery is called, result empty list, with view loading state, should change playersSearchState to PlayerResultSuccess`() {
        whenever(fetchPlayersInteractor.getPlayersByQuery(any())).thenReturn(Single.just(mockPlayers(true)))
        viewModel.advanceToSearchPlayers(scheduler)
        TestCase.assertTrue(viewModel.playersSearchState.value is PlayerSearchState.PlayerResultSuccess)
        val players = (viewModel.playersSearchState.value as PlayerSearchState.PlayerResultSuccess).players
        TestCase.assertTrue(players.isEmpty())
    }

    @Test
    fun `when getPlayersByQuery is called with error, and view loading state, should change playersSearchState to PlayerResultFailure`() {
        whenever(fetchPlayersInteractor.getPlayersByQuery(any())).thenReturn(Single.error(Throwable("test error")))
        viewModel.advanceToSearchPlayers(scheduler)
        TestCase.assertTrue(viewModel.playersSearchState.value is PlayerSearchState.PlayerResultFailure)
    }

    @Test
    fun `when navigateToPlayerDetail is called, then check navigator navigateToPlayerDetail is called`() {
        doNothing().whenever(navigator).navigateToPlayerDetail(any())
        viewModel.navigateToPlayerDetail(PLAYER_2)
        verify(navigator).navigateToPlayerDetail(any())
    }

    private fun PlayersSearchViewModel.advanceToFetchAllPlayers(scheduler: TestScheduler) {
        { this.getAllPlayers() } andTrigger scheduler
        { (this.playersSearchState.value as PlayerSearchState.Loading).loadedAction.invoke() } andTrigger scheduler
    }

    private fun PlayersSearchViewModel.advanceToSearchPlayers(scheduler: TestScheduler) {
        { this.searchPlayersByQuery("test") } andTrigger scheduler
        { (this.playersSearchState.value as PlayerSearchState.Loading).loadedAction.invoke() } andTrigger scheduler
    }

    private fun mockPlayers(needsEmptyList: Boolean, vararg players: PlayerUiModel): List<PlayerUiModel> {
        return if (needsEmptyList) {
            emptyList()
        } else {
            players.asList()
        }
    }

    private fun mockPlayer(id: Int): PlayerUiModel {
        return PlayerUiModel(
            id,
            "player name",
            TeamUiModel(
                1,
                "city",
                "conference",
                "name",
                "LAL"
            ),
            "C-F",
            1,
            1
        )
    }

}
