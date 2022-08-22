package com.team8.interfaces

import com.team8.domain.RoundDTO

interface IUpdateMatchUseCase {
    operator fun invoke(round: RoundDTO)

}
