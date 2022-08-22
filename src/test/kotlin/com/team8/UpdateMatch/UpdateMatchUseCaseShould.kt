package com.team8.UpdateMatch

import com.team8.domain.Match
import com.team8.domain.MatchTurn
import com.team8.domain.RoundStatus
import com.team8.interfaces.IMatchRepository
import com.team8.interfaces.IUpdateMatchUseCase
import junit.framework.TestCase.*
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.test.assertNotNull

class UpdateMatchUseCaseShould {

    @Test
    fun `Check if the round status is finished in the given match`()
    {
        val match = SetNewMatch()
        val status = match.rounds[0].roundStatus

        //Assert
        assertEquals(status, RoundStatus.NotStarted)
    }
    @Test
    fun `Change user turn when the answers are complete`()
    {

        val match = SetNewMatch()
        match.SetAnswers(answers = arrayOf("","",""))
        val matchTurn = match.matchTurn
        //Assert
        assertEquals(matchTurn, MatchTurn.Opponent)
    }

    @Test
    fun `Set challenger Answers to Round`()
    {
        val answers = arrayOf<String>()
        val match = SetNewMatch()
        match.SetAnswers(answers)

        //
        assertEquals(match.rounds[0].challengerAnswers, answers)
    }

    @Test
    fun `Set opponent Answers to Round`()
    {
        val answers = arrayOf<String>()
        val match = SetNewMatch()
        match.SetAnswers(answers)
        match.SetAnswers(answers)

        //
        assertEquals(match.rounds[0].opponentAnswers, answers)
    }

    @Test
    fun `Set the answers depending of the matchturn`()
    {
        val answers = arrayOf<String>()
        val answers2 = arrayOf<String>()
        val match = SetNewMatch()
        match.SetAnswers(answers)

        assertEquals(answers,match.rounds[0].challengerAnswers)

        match.SetAnswers(answers2)

        assertEquals(answers2,match.rounds[0].opponentAnswers)

    //assertNull(match.rounds[0].opponentAnswers[0])
    }

    fun SetNewMatch() : Match
    {
        val match = Match("Theo", "Ricardo")
        return match
    }

}