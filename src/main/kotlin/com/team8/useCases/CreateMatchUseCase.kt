package com.team8.useCases

import com.team8.interfaces.ICreateMatchUseCase
import com.team8.domain.Match
import com.team8.interfaces.IMakeMatch
import kotlinx.coroutines.*

class CreateMatchUseCase(val matchmakingService: IMakeMatch) : ICreateMatchUseCase {

    override suspend operator fun invoke(challenger: String): Match {
        val opponent = matchmakingService.GetOpponent(challenger)
        return Match(challenger, opponent)
        /*
        var match : Match
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val deferredOpponent = async {
                matchmakingService.GetOpponent(challenger)
            }
            match = Match(challenger, deferredOpponent.await())
        }
        return match

         */
    }

}