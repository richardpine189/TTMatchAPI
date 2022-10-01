package com.team8.useCases.getMatchByID

import com.team8.match.domain.DTO.ActiveMatchDTO
import com.team8.match.domain.Match
import com.team8.match.repository.IMatchRepository
import com.team8.match.useCases.getMatchByID.GetMatchUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertNotNull

internal class GetMatchUseCaseShould
{
    val matchRepository = mock<IMatchRepository>()
    @Test
    fun `return an ActiveMatchDTO when is called`() = runTest {

            val matchID = 0;
            val match = Match("Challenger", "Opponent")
            whenever(matchRepository.getMatch(matchID)).thenReturn(match)
            val getMatch = GetMatchUseCase(matchRepository)
            val activeMatch = getMatch(0)

            assertNotNull(getMatch)
            val activeMatchDTO = ActiveMatchDTO(
                match.challenger,
                match.opponent,
                match.currentRound,
                match.rounds[match.currentRound].letter!!,
                match.rounds[match.currentRound].timeLeft!!,
                match.rounds[match.currentRound].categoryNames
            )
    }
}