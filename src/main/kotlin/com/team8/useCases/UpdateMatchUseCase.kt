package com.team8.useCases

import com.team8.domain.Round
import com.team8.interfaces.IMatchRepository
import com.team8.interfaces.IUpdateMatchUseCase
import com.team8.repository.MatchRepository

class UpdateMatchUseCase(private val repository: IMatchRepository) : IUpdateMatchUseCase{
    override operator fun invoke(id : Int, round: Round)
    {
        //obtener match
        val match = repository.getMatch(id)
        //setearle cambios

        //guardar
    }
}
