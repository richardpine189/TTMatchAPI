package com.team8.interfaces

import com.team8.domain.Round

interface IUpdateMatchUseCase {
    operator fun invoke(id : Int, round: Round)

}
