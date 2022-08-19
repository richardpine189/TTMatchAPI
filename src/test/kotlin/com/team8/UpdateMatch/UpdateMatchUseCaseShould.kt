package com.team8.UpdateMatch

import com.team8.domain.Match
import com.team8.domain.MatchTurn
import com.team8.domain.RoundStatus
import com.team8.interfaces.IMatchRepository
import com.team8.interfaces.IUpdateMatchUseCase
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class UpdateMatchUseCaseShould {

    @Test
    fun `Check if the round status is finished in the given match`()
    {
        val match = Match("Theo", "Ricardo")
        val status = match.rounds[0].roundStatus

        //Assert
        assertEquals(status, RoundStatus.NotStarted)
    }
    @Test
    fun `Change user turn when the answers are complete`()
    {
        val match = Match("Theo", "Ricardo")

        match.SetAnswers()
        val matchTurn = match.matchTurn
        //Assert
        assertEquals(matchTurn, MatchTurn.Opponent)
    }
}