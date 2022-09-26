package com.team8.models


import com.team8.match.domain.Match
import com.team8.match.domain.WinnerStatus
import org.junit.Test
import kotlin.test.assertEquals

class RoundShould {
    @Test
    fun `get round winner challenger`()
    {
        val match = Match("test", "test2")
        match.setResults(arrayOf(true, false, false, false, false))
        match.endTurn()
        match.setResults(arrayOf(false, false, false, false, false))
        match.endTurn()
        match.rounds[0].updateRoundStatus()

        val round = match.rounds[0]

        assertEquals(WinnerStatus.Challenger, round.winner)
    }

    @Test
    fun `get round winner opponent`()
    {
        val match = Match("test", "test2")
        match.setResults(arrayOf(false, false, false, false, false))
        match.endTurn()
        match.setResults(arrayOf(true, false, false, false, false))
        match.endTurn()
        //match.rounds[0].updateRoundStatus()
        val round = match.rounds[0]

        assertEquals(WinnerStatus.Opponent, round.winner)
    }

    @Test
    fun `get round winner draw`()
    {
        val match = Match("test", "test2")
        match.setResults(arrayOf(true, false, false, false, false))
        match.endTurn()
        match.setResults(arrayOf(true, false, false, false, false))
        match.endTurn()
        match.rounds[0].updateRoundStatus()
        val round = match.rounds[0]

        assertEquals(WinnerStatus.Draw, round.winner)
    }

    @Test
    fun `get unassigned winner when the round is not done yet`()
    {
        val match = Match("test", "test2")
        match.setResults(arrayOf(true, false, false, false, false))
        match.rounds[0].updateRoundStatus()
        val round = match.rounds[0]

        assertEquals(WinnerStatus.Unassigned, round.winner)
    }
}