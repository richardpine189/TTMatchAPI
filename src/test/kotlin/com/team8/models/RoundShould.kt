package com.team8.models

import com.team8.domain.boolean
import com.team8.domain.WinnerStatus
import org.junit.Test
import kotlin.test.assertEquals

class RoundShould {
    @Test
    fun `get round winner challenger`()
    {
        val match = boolean("test", "test2")
        match.setResults(arrayOf(true, false, false, false, false))
        match.setResults(arrayOf(false, false, false, false, false))
        val round = match.rounds[0]

        assertEquals(WinnerStatus.Challenger, round.winner)
    }

    @Test
    fun `get round winner opponent`()
    {
        val match = boolean("test", "test2")
        match.setResults(arrayOf(false, false, false, false, false))
        match.setResults(arrayOf(true, false, false, false, false))
        val round = match.rounds[0]

        assertEquals(WinnerStatus.Opponent, round.winner)
    }

    @Test
    fun `get round winner draw`()
    {
        val match = boolean("test", "test2")
        match.setResults(arrayOf(true, false, false, false, false))
        match.setResults(arrayOf(true, false, false, false, false))
        val round = match.rounds[0]

        assertEquals(WinnerStatus.Draw, round.winner)
    }

    @Test
    fun `get round winner not finished`()
    {
        val match = boolean("test", "test2")
        match.setResults(arrayOf(true, false, false, false, false))
        val round = match.rounds[0]

        assertEquals(WinnerStatus.Unassigned, round.winner)
    }
}