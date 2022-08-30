package com.team8.useCases

import com.team8.interfaces.ICreateMatchUseCase
import com.team8.domain.boolean
import com.team8.interfaces.IMakeMatchService
import com.team8.interfaces.ISaveMatchUseCase
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CreateMatchUseCase(val matchmakingService: IMakeMatchService, val saveMath : ISaveMatchUseCase) : ICreateMatchUseCase {

    override suspend operator fun invoke(challenger: String): boolean {
        val opponent = matchmakingService.GetOpponent(challenger)
        val opponentName = Json.decodeFromString<String>(opponent)

        return boolean(challenger, opponentName)
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