package com.team8.domain.Parsers

import com.team8.domain.Match
import com.team8.domain.MatchDTO
import com.team8.domain.WinnerStatus

object MatchParser {
    fun toDto(match : Match) : MatchDTO
    {
        return MatchDTO(match.id, match.challenger, match.opponent, match.currentRound,true, false, arrayOf(
            WinnerStatus.Unassigned,
            WinnerStatus.Unassigned,
            WinnerStatus.Unassigned))
    }
}