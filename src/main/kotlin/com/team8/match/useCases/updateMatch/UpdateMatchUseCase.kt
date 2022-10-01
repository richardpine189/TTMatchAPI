package com.team8.match.useCases.updateMatch

import com.team8.match.domain.Match
import com.team8.match.domain.DTO.RoundDTO
import com.team8.match.domain.RoundStatus
import com.team8.match.domain.WinnerStatus
import com.team8.match.repository.IMatchRepository
import com.team8.match.useCases.updateMatch.service.ISetVictoryService

class UpdateMatchUseCase(private val repository: IMatchRepository, private val service: ISetVictoryService) :
    IUpdateMatchUseCase {
    override suspend operator fun invoke(roundDTO: RoundDTO) : Boolean
    {
        //obtener match
        val match = repository.getMatch(roundDTO.idMatch)

        //setearle cambios
        match.setCategories(roundDTO.categories)

        match.setAnswers(roundDTO.answers)

        match.setResults(roundDTO.results)

        match.setLetter(roundDTO.letter)

        match.setTimeLeft(roundDTO.timeLeft)

        match.endTurn()

        //guardar
        repository.saveMatch(match)

        if(match.winner != WinnerStatus.Unassigned)
        {
            //MATCHSERVICE ->USER
            callVictoryService(match)
            return true;
        }

        return match.rounds[match.currentRound].roundStatus != RoundStatus.Unfinished
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
