package com.team8.useCases

import com.team8.interfaces.ICreateMatchUseCase
import com.team8.domain.Match
import com.team8.interfaces.IMakeMatchService
import com.team8.interfaces.ISaveMatchUseCase
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CreateMatchUseCase(val matchmakingService: IMakeMatchService, val saveMatch : ISaveMatchUseCase) : ICreateMatchUseCase {

    override suspend operator fun invoke(challenger: String): Match {
        val opponent = matchmakingService.GetOpponent(challenger)
        val opponentName = Json.decodeFromString<String>(opponent)

        // TODO : guardar match

        return Match(challenger, opponentName)
    }
}