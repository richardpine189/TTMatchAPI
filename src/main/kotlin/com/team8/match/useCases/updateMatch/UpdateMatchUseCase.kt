package com.team8.match.useCases.updateMatch

import com.team8.match.domain.Match
import com.team8.match.domain.DTO.RoundDTO
import com.team8.match.domain.RoundStatus
import com.team8.match.domain.WinnerStatus
import com.team8.match.repository.IMatchRepository
import com.team8.match.useCases.updateMatch.service.ISetVictoryService

class UpdateMatchUseCase(private val repository: IMatchRepository, private val service: ISetVictoryService) :
    IUpdateMatchUseCase {
    override suspend operator fun invoke(round: RoundDTO) : Boolean
    {
        //obtener match
        val match = repository.getMatch(round.id)

        //setearle cambios
        match.setCategories(round.categories)

        match.setAnswers(round.answers)

        match.setResults(round.results)

        match.setLetter(round.letter)

        match.setTimeLeft(round.timeLeft)

        match.endTurn()

        //guardar
        repository.saveMatch(match)

        //MATCHSERVICE ->USER
        CallVictoryService(match)

        if(match.winner != WinnerStatus.Unassigned)
        {
            return true;
        }

        return match.rounds[match.currentRound].roundStatus != RoundStatus.Unfinished
    }

    private suspend fun CallVictoryService(match: Match)
    {
        if(match.winner == WinnerStatus.Challenger)
            service.setVictory(match.challenger)
        else if(match.winner == WinnerStatus.Opponent)
            service.setVictory(match.opponent)
        else(match.winner == WinnerStatus.Draw)
        //Devolver ALGO, CONSULTAR
    }
}
