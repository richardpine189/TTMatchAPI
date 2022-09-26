package com.team8.match.domain.Parsers

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
}