package com.team8.match.useCases.resetMatchesHandler

import com.team8.match.repository.IMatchRepository

class ResetMatchesUseCase(val repository : IMatchRepository) : IResetMatchesUseCase {
    override suspend fun invoke() {
        repository.resetRepository()
    }
}