package com.team8.match.useCases.requestReMatch

import com.team8.match.domain.Match
import com.team8.match.repository.IMatchRepository

class ReMatchUseCase(val matchRepository: IMatchRepository) : IReMatchUseCase {

    override suspend operator fun invoke(challenger : String,opponent : String) : Match{
        val match = Match(challenger, opponent)
        matchRepository.saveMatch(match)
        return match
    }
}