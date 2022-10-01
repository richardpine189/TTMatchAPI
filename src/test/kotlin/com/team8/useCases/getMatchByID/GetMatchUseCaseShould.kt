package com.team8.useCases.getMatchByID

import com.team8.match.domain.DTO.ActiveMatchDTO
import com.team8.match.domain.Match
import com.team8.match.repository.IMatchRepository
import com.team8.match.useCases.getMatchByID.GetMatchUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertNotNull

internal class GetMatchUseCaseShould
{

    private val matchRepository = mock<IMatchRepository>()
    private var matchID = 0
    private lateinit var activeMatch : ActiveMatchDTO

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    @Test
    fun `return an ActiveMatchDTO when is called`() = runTest{

            matchID = 0

            declareMatchToRequest()

            getActiveMatch(matchID)

            assertNotNull(activeMatch)
    }

    //Test For Handler
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    @Test
    fun `Check the id is not negative`() = runTest{


        assertThrows<Exception> {

            matchID = -1

            declareMatchToRequest()

            getActiveMatch(matchID)

        }
    }

    private fun declareMatchToRequest()
    {
        val match = Match("Challenger", "Opponent")
        whenever(matchRepository.getMatch(matchID)).thenReturn(match)
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    private fun getActiveMatch(matchID : Int) = runTest{
        val getMatch = GetMatchUseCase(matchRepository)
        activeMatch = getMatch(matchID)
    }
}