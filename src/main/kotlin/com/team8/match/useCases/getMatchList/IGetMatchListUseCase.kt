package com.team8.match.useCases.getMatchList

import com.team8.match.domain.DTO.OnGoingMatchDTO

interface IGetMatchListUseCase {
    suspend operator fun invoke(userId : String) : MutableList<OnGoingMatchDTO>
}



