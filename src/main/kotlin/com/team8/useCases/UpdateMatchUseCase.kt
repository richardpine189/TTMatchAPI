package com.team8.useCases

import com.team8.domain.Match
import com.team8.domain.RoundDTO
import com.team8.interfaces.IMatchRepository
import com.team8.interfaces.ISetVictoryService
import com.team8.interfaces.IUpdateMatchUseCase

class UpdateMatchUseCase(private val repository: IMatchRepository, private val service: ISetVictoryService) : IUpdateMatchUseCase{
    override operator fun invoke(round: RoundDTO) : Match
    {
        //obtener match
        val match = repository.getMatch(round.id)

        //setearle cambios
        match.setCategories(round.categories)

        match.setAnswers(round.answers)

        match.setResults(round.results)

        //guardar
        repository.saveMatch(match)
        //MATCHSERVICE ->USER


        return match
    }
}
