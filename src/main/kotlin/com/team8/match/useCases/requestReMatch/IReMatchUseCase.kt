package com.team8.match.useCases.requestReMatch

import com.team8.match.domain.Match

interface IReMatchUseCase {
    suspend operator fun invoke(challenger : String,opponent : String) : Match
}