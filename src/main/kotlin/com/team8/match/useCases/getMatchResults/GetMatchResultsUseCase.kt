package com.team8.match.useCases.getMatchResults

import com.team8.match.domain.DTO.MatchResultsDTO
import com.team8.match.repository.IMatchRepository

class GetMatchResultsUseCase(val matchRepository: IMatchRepository) : IGetMatchResultsUseCase {

    override suspend operator fun  invoke(idMatch : Int,roundIndex : Int) : MatchResultsDTO {

        val match = matchRepository.getMatch(idMatch)

        val matchResultsDTO = MatchResultsDTO(
            match.rounds[roundIndex].categoryNames,
            match.rounds[roundIndex].challengerAnswers,
            match.rounds[roundIndex].opponentAnswers,
            match.rounds[roundIndex].challengerResults,
            match.rounds[roundIndex].opponentResults
        )

        return matchResultsDTO
    }
}