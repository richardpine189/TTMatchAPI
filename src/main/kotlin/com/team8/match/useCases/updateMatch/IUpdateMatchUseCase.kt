package com.team8.match.useCases.updateMatch

import com.team8.match.domain.DTO.RoundDTO

interface IUpdateMatchUseCase {
    suspend operator fun invoke(round: RoundDTO) : Boolean

}
