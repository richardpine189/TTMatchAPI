package com.team8.match.domain.Parsers

import com.team8.match.domain.DTO.ActiveMatchDTO
import com.team8.match.domain.Match
import com.team8.match.domain.DTO.MatchDTO
import com.team8.match.domain.WinnerStatus

object MatchParser {
    fun toDto(match : Match) : MatchDTO
    {
        return MatchDTO(match.id, match.challenger, match.opponent, match.currentRound,true, false, arrayOf(
            WinnerStatus.Unassigned,
            WinnerStatus.Unassigned,
            WinnerStatus.Unassigned))
    }

    fun toActiveMatchDto(match: Match) : ActiveMatchDTO
    {
        val activeMatchDTO = ActiveMatchDTO(
            match.challenger,
            match.opponent,
            match.currentRound,
            match.rounds[match.currentRound].letter,
            match.rounds[match.currentRound].timeLeft,
            match.rounds[match.currentRound].categoryNames,
        )
        return activeMatchDTO
    }
}