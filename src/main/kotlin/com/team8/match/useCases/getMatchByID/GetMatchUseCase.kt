package com.team8.match.useCases.getMatchByID

import com.team8.match.domain.DTO.ActiveMatchDTO
import com.team8.match.repository.IMatchRepository

class GetMatchUseCase(val matchRespository : IMatchRepository) : IGetMatchUseCase {

    override suspend fun invoke(matchId : Int) : ActiveMatchDTO
    {
        val match = matchRespository.getMatch(matchId)

        val activeMatchDTO = ActiveMatchDTO(
            match.challenger,
            match.opponent,
            match.currentRound,
            match.rounds[match.currentRound].letter!!,
            match.rounds[match.currentRound].timeLeft!!,
            match.rounds[match.currentRound].categoryNames,
        )

        return activeMatchDTO
    }
}