package com.team8.match.useCases.getMatchResults

import com.team8.match.domain.DTO.MatchResultsDTO

interface IGetMatchResultsUseCase {
    suspend operator fun  invoke(idMatch : Int) : MatchResultsDTO
}