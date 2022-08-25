package com.team8.useCases

import com.team8.domain.Match
import com.team8.domain.RoundDTO
import com.team8.domain.WinnerStatus
import com.team8.interfaces.IMatchRepository
import com.team8.interfaces.ISetVictoryService
import com.team8.interfaces.IUpdateMatchUseCase
import java.awt.event.WindowEvent

class UpdateMatchUseCase(private val repository: IMatchRepository, private val service: ISetVictoryService) : IUpdateMatchUseCase{
    override suspend operator  fun invoke(round: RoundDTO) : Match
    {
        //obtener match
        val match = repository.getMatch(round.id)

        //setearle cambios
        match.setCategories(round.categories)

        match.setAnswers(round.answers)

        match.setResults(round.results)

        match.setLetter(round.letter)

        match.setTimeLeft(round.timeLeft)

        //guardar
        repository.saveMatch(match)


        //MATCHSERVICE ->USER
        CallVictoryService(match)

        return match
    }

    private suspend fun CallVictoryService(match:Match)
    {
        if(match.winner == WinnerStatus.Challenger)
            service.setVictory(match.challenger)
        else if(match.winner == WinnerStatus.Opponent)
            service.setVictory(match.opponent)
        else(match.winner == WinnerStatus.Draw)
        //Devolver ALGO, CONSULTAR
    }
}
