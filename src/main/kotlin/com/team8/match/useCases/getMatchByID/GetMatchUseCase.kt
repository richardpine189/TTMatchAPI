package com.team8.match.useCases.getMatchByID

import com.team8.match.domain.DTO.ActiveMatchDTO
import com.team8.match.domain.Parsers.MatchParser
import com.team8.match.repository.IMatchRepository

class GetMatchUseCase(val matchRespository : IMatchRepository) : IGetMatchUseCase {

    override suspend fun invoke(matchId : Int) : ActiveMatchDTO
    {
        val match = matchRespository.getMatch(matchId)

        val activeMatchDTO = MatchParser.toActiveMatchDto(match)

        return activeMatchDTO
    }
}