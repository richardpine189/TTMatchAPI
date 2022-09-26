package com.team8.match.useCases.getMatchByID

import com.team8.match.domain.DTO.ActiveMatchDTO

interface IGetMatchUseCase {
    suspend operator fun invoke(matchId : Int) : ActiveMatchDTO
}

