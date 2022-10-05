package com.team8.useCases.createMatch

import com.team8.match.useCases.createMatch.service.IMakeMatchService
import com.team8.match.useCases.createMatch.service.MatchmakingService
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNotNull

class matchmakingServiceShould {

    private lateinit var matchmakingService : IMakeMatchService
    private lateinit var opponent : String
    @Test
    fun `return opponent name when requested`() = runTest {
        // Arrange
        CreateService()

        // Act
        RequestOpponent()

        // Assert
        assertNotNull(opponent)
    }

    // TODO: Como simular una API CAIDA?
    @Test
    fun `throw an Exception when the services is not avalible`() = runTest{

        matchmakingService = MatchmakingService("c")

        assertThrows<Exception> { RequestOpponent() }
    }

    private fun CreateService()
    {
        matchmakingService = MatchmakingService("https://fathomless-fortress-23469.herokuapp.com")
    }

    private fun RequestOpponent() = runTest {
        opponent = matchmakingService.GetOpponent("Theo")
        //responseUser = Json.decodeFromString<String>(responseJson)
    }
}

