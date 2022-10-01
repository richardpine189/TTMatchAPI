package com.team8.match.useCases.createMatch

import com.team8.match.domain.Match
import com.team8.match.useCases.createMatch.service.IMakeMatchService
import com.team8.match.repository.IMatchRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder

class CreateMatchUseCase(private val matchmakingService: IMakeMatchService, private val matchRepository: IMatchRepository) :
    ICreateMatchUseCase {

    override suspend operator fun invoke(challenger: String): Match {
        val opponent = matchmakingService.GetOpponent(challenger)

        val match = Match(challenger, opponent)

        matchRepository.saveMatch(match)

        return match
    }
}