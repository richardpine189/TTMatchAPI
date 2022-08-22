package com.team8.models

import com.team8.domain.Match
import com.team8.domain.MatchTurn
import com.team8.domain.RoundStatus
import junit.framework.TestCase.*
import org.junit.Test

class MatchShould {

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
        match.setAnswers(answers = arrayOf("","",""))
        val matchTurn = match.matchTurn
        //Assert
        assertEquals(matchTurn, MatchTurn.Opponent)
    }

    @Test
    fun `Set challenger Answers to Round`()
    {
        val answers = arrayOf<String>()
        val match = SetNewMatch()
        match.setAnswers(answers)

        //
        assertEquals(match.rounds[0].challengerAnswers, answers)
    }

    @Test
    fun `Set opponent Answers to Round`()
    {
        val answers = arrayOf<String>()
        val match = SetNewMatch()
        match.setAnswers(answers)
        match.setAnswers(answers)

        //
        assertEquals(match.rounds[0].opponentAnswers, answers)
    }

    @Test
    fun `Set the challenger answers depending of the matchturn`()
    {
        var checkAnswer = true
        val answers = arrayOf<String>("Hola","Theo","Que","Tal","Estas")
        val match = SetNewMatch()
        match.setAnswers(answers)

        for(i in 0..4)
        {
            if(match.rounds[0].challengerAnswers[i] != answers[i])
                checkAnswer = false
        }

        assertEquals(true, checkAnswer)
    }
    @Test
    fun `Set the challenger opponent depending of the matchturn`()
    {
        var checkAnswer = true
        val answers = arrayOf<String>()
        val answers2 = arrayOf<String>("Hola","Theo","Que","Tal","Estas")
        val match = SetNewMatch()
        match.setAnswers(answers)
        match.setAnswers(answers2)
        for(i in 0..4)
        {
            if(match.rounds[0].opponentAnswers[i] != answers2[i])
                checkAnswer = false
        }

        assertEquals(true, checkAnswer)
    }
    @Test
    fun `Move to the next round when the previous its finished`()
    {
        val roundExpectedIndex = 1 // It`s the second round
        val answers = arrayOf<String>()
        val match = SetNewMatch()
        match.setAnswers(answers)
        match.setAnswers(answers)
        assertEquals(roundExpectedIndex, match.currentRound)
    }

    @Test
    fun `Set categories to round`()
    {
        var result = true
        val categories = arrayOf<String>("Hola","Theo","Que","Tal","Estas")
        val match = SetNewMatch()

        match.setCategories(categories)

        for(i in 0..4)
        {
            if(match.rounds[0].categoryNames[i] != categories[i])
            {
                result = false
            }
        }

        assertTrue(result)
    }

    @Test
    fun `Set answerResults to round`()
    {
        var result = true
        val answerResults = arrayOf<Boolean>(true, false, true, true, false)
        val match = SetNewMatch()

        match.setResults(answerResults)

        for(i in 0..4)
        {
            if(match.rounds[0].challengerResults[i] != answerResults[i])
            {
                result = false
            }
        }

        assertTrue(result)
    }

    fun SetNewMatch() : Match
    {
        val match = Match("Theo", "Ricardo")
        return match
    }
}