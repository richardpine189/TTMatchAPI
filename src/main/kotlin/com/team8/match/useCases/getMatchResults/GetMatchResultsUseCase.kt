package com.team8.match.useCases.getMatchResults

import com.team8.match.domain.DTO.MatchResultsDTO
import com.team8.match.domain.RoundStatus
import com.team8.match.repository.IMatchRepository

class GetMatchResultsUseCase(val matchRepository: IMatchRepository) : IGetMatchResultsUseCase {

    override suspend operator fun  invoke(idMatch : Int) : MatchResultsDTO {

        val match = matchRepository.getMatch(idMatch)

        val round = match.rounds.last{it.roundStatus == RoundStatus.Finished}

        /*val matchResultsDTO = MatchResultsDTO(
            match.rounds[roundIndex].categoryNames,
            match.rounds[roundIndex].challengerAnswers,
            match.rounds[roundIndex].opponentAnswers,
            match.rounds[roundIndex].challengerResults,
            match.rounds[roundIndex].opponentResults
        )*/
        // TODO: LLEVAR AL PARSEADOR
        val matchResultsDTO = MatchResultsDTO(
            round.categoryNames,
            round.challengerAnswers,
            round.opponentAnswers,
            round.challengerResults,
            round.opponentResults,
            match.winner
        )

        return matchResultsDTO
    }
}