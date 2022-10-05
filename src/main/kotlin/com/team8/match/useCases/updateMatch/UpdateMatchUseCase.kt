package com.team8.match.useCases.updateMatch

import com.team8.match.domain.DTO.RoundDTO
import com.team8.match.domain.Match
import com.team8.match.domain.RoundStatus
import com.team8.match.domain.WinnerStatus
import com.team8.match.repository.IMatchRepository
import com.team8.match.useCases.updateMatch.service.ISetVictoryService

class UpdateMatchUseCase(private val repository: IMatchRepository, private val service: ISetVictoryService) :
    IUpdateMatchUseCase {
    override suspend operator fun invoke(roundDTO: RoundDTO) : Boolean
    {

        val match = repository.getMatch(roundDTO.idMatch)

        match.updateMatch(roundDTO)

        repository.saveMatch(match)

        val matchHasFinished = match.matchHasWinner()

        if(matchHasFinished)
        {
            callVictoryService(match)
            return true;
        }

        return match.roundHasEnded()
    }

    private suspend fun callVictoryService(match: Match)
    {
        if(match.winner == WinnerStatus.Challenger)
            service.setVictory(match.challenger)
        else if(match.winner == WinnerStatus.Opponent)
            service.setVictory(match.opponent)
        else(match.winner == WinnerStatus.Draw)
        //Devolver ALGO, CONSULTAR
    }
}
