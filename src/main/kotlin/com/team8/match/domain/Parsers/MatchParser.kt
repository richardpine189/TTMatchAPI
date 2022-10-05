package com.team8.match.domain.Parsers

import com.team8.match.domain.DTO.ActiveMatchDTO
import com.team8.match.domain.Match
import com.team8.match.domain.DTO.MatchDTO
import com.team8.match.domain.DTO.OnGoingMatchDTO
import com.team8.match.domain.MatchTurn
import com.team8.match.domain.RoundStatus
import com.team8.match.domain.WinnerStatus

object MatchParser {
    fun toDto(match : Match) : MatchDTO
    {
        return MatchDTO(
            match.id,
            match.challenger,
            match.opponent,
            match.currentRound,
            true,
            false,
            arrayOf(WinnerStatus.Unassigned,WinnerStatus.Unassigned,WinnerStatus.Unassigned))
            //arrayOf( match.rounds[0].winner,match.rounds[1].winner,match.rounds[2].winner)
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

    fun toOngoingMatchDto(match: Match) : OnGoingMatchDTO
    {
        val onGoingMatchDto = OnGoingMatchDTO(
            match.id,
            match.challenger,
            match.opponent,
            match.currentRound,
            (match.matchTurn == MatchTurn.Challenger),
            match.matchHasWinner(), //match.winner != WinnerStatus.Unassigned,
            arrayOf(match.rounds[0].winner, match.rounds[1].winner, match.rounds[2].winner)
        )

        return onGoingMatchDto
    }
}