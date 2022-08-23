package com.team8.useCases

import com.team8.domain.Match
import com.team8.domain.RoundDTO
import com.team8.interfaces.IMatchRepository
import com.team8.interfaces.IUpdateMatchUseCase

class UpdateMatchUseCase(private val repository: IMatchRepository) : IUpdateMatchUseCase{
    override operator fun invoke(round: RoundDTO) : Match
    {
        //obtener match
        val match = repository.getMatch(round.id)

        //setearle cambios
        match.setCategories(round.categories)
        match.setResults(round.results)
        match.setAnswers(round.answers)

        //guardar
        repository.saveMatch(match)

        return match
    }
}
