package com.team8.useCases

import com.team8.interfaces.ICreateMatchUseCase
import com.team8.domain.Match
import com.team8.interfaces.IMakeMatch
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CreateMatchUseCase(val matchmakingService: IMakeMatch) : ICreateMatchUseCase {

    override suspend operator fun invoke(challenger: String): Match {
        val opponent = matchmakingService.GetOpponent(challenger)
        val opponentName = Json.decodeFromString<String>(opponent)
        return Match(challenger, opponentName)
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