package com.team8.CreateMatch

import com.team8.services.MatchmakingService
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import org.junit.Test
import kotlin.test.assertNotNull

class matchmakingServiceShould {


    @Test
    fun `return opponent name when requested`() = runTest {
        // Arrange
        val matchmakingService = MatchmakingService("https://fathomless-fortress-23469.herokuapp.com")

        // Act
        val responseJson = matchmakingService.GetOpponent("Theo")
        val responseUser = Json.decodeFromString<String>(responseJson)
        // Assert
        assertNotNull(responseUser)
    }
}

