package com.team8.CreateMatch

import com.team8.interfaces.IMakeMatchService
import com.team8.match.CreateMatch.service.MatchmakingService
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNotNull

class matchmakingServiceShould {

    private lateinit var matchmakingService : IMakeMatchService
    private lateinit var responseUser : String
    @Test
    fun `return opponent name when requested`() = runTest {
        // Arrange
        CreateService()

        // Act
        RequestOpponent()

        // Assert
        assertNotNull(responseUser)
    }

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
        val responseJson = matchmakingService.GetOpponent("Theo")
        responseUser = Json.decodeFromString<String>(responseJson)
    }
}

