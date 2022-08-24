package com.team8.interfaces

import com.team8.domain.Match
import com.team8.domain.RoundDTO

interface IUpdateMatchUseCase {
    suspend operator fun invoke(round: RoundDTO) : Match

}
