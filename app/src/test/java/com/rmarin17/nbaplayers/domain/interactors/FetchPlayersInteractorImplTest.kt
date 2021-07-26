package com.rmarin17.nbaplayers.domain.interactors

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rmarin17.nbaplayers.data.network.PlayerServices
import com.rmarin17.nbaplayers.data.network.models.DataResponseModel
import com.rmarin17.nbaplayers.data.network.models.PlayerResponseModel
import com.rmarin17.nbaplayers.data.network.models.TeamResponseModel
import com.rmarin17.nbaplayers.test.RxTestSchedulerRule
import com.rmarin17.nbaplayers.ui.models.PlayerUiModel.Companion.MOCK_PLAYER_IMAGE
import io.reactivex.rxjava3.core.Observable
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Unit test of FetchPlayersInteractorImplTest.
 */
@RunWith(MockitoJUnitRunner::class)
class FetchPlayersInteractorImplTest {

    private val playersService: PlayerServices = mock()

    private lateinit var fetchPlayersInteractor: FetchPlayersInteractor

    private val PLAYER_1 = mockPlayerResponseModel(1)

    @get:Rule
    val testScheduler = RxTestSchedulerRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        fetchPlayersInteractor = FetchPlayersInteractorImpl(playersService)
    }

    @Test
    fun `when getAllPlayers is called and result is success, the check response`() {
        whenever(playersService.getPlayers()).thenReturn(Observable.just(mockDataResponseModel()))
        val result = fetchPlayersInteractor.getAllPlayers().blockingGet().first()
        TestCase.assertTrue(result.id == PLAYER_1.id)
        TestCase.assertTrue(result.name == "${PLAYER_1.firstName} ${PLAYER_1.lastName}")
        TestCase.assertTrue(result.image == MOCK_PLAYER_IMAGE)
        TestCase.assertTrue(result.team == PLAYER_1.team.transformToTeamUiModel())
        TestCase.assertTrue(result.position == PLAYER_1.position)
        TestCase.assertTrue(result.weight == PLAYER_1.weightPounds)
        TestCase.assertTrue(result.height == PLAYER_1.heightInches)
    }

    @Test(expected = Throwable::class)
    fun `when getAllPlayers is called and result is error, the check response`() {
        whenever(playersService.getPlayers()).thenReturn(Observable.error(Throwable("test")))
        fetchPlayersInteractor.getAllPlayers().blockingGet()
    }

    @Test
    fun `when getPlayersByQuery is called and result is success, the check response`() {
        whenever(playersService.getPlayersByQuery(any())).thenReturn(Observable.just(mockDataResponseModel()))
        val result = fetchPlayersInteractor.getPlayersByQuery("test").blockingGet().first()
        TestCase.assertTrue(result.id == PLAYER_1.id)
        TestCase.assertTrue(result.name == "${PLAYER_1.firstName} ${PLAYER_1.lastName}")
        TestCase.assertTrue(result.image == MOCK_PLAYER_IMAGE)
        TestCase.assertTrue(result.team == PLAYER_1.team.transformToTeamUiModel())
        TestCase.assertTrue(result.position == PLAYER_1.position)
        TestCase.assertTrue(result.weight == PLAYER_1.weightPounds)
        TestCase.assertTrue(result.height == PLAYER_1.heightInches)
    }

    @Test(expected = Throwable::class)
    fun `when getPlayersByQuery is called and result is error, the check response`() {
        whenever(playersService.getPlayersByQuery(any())).thenReturn(Observable.error(Throwable("test")))
        fetchPlayersInteractor.getPlayersByQuery("test").blockingGet()
    }

    private fun mockDataResponseModel(): DataResponseModel {
        return DataResponseModel(
            listOf(PLAYER_1)
        )
    }

    private fun mockPlayerResponseModel(id: Int): PlayerResponseModel {
        return PlayerResponseModel(
            id = id,
            firstName = "name",
            lastName = "last name",
            heightFeet = 1,
            heightInches = 2,
            position = "C-F",
            team = TeamResponseModel(
                id = 1,
                abbreviation = "LAL",
                city = "Los Angeles",
                conference = "east",
                division = "test",
                fullName = "full name",
                name = "name"
            ),
            weightPounds = 1
        )
    }
}
