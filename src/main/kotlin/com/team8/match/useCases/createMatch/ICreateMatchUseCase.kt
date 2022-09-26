package com.team8.match.useCases.createMatch

import com.team8.match.domain.Match

interface ICreateMatchUseCase {
    suspend operator fun invoke(challenger : String) : Match
}