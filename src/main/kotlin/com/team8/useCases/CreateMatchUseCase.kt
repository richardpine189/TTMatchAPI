package com.team8.useCases

import com.team8.interfaces.ICreateMatchUseCase
import com.team8.domain.Match
import com.team8.interfaces.IMakeMatch

class CreateMatchUseCase(val matchmakingService: IMakeMatch) : ICreateMatchUseCase {

    override operator fun invoke(challenger: String): Match {
        val opponent = matchmakingService.GetOpponent()
        return Match(challenger, opponent)
    }
}