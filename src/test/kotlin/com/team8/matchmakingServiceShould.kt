package com.team8

import com.team8.services.MatchmakingService
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import org.junit.Test
import kotlin.test.assertNotNull

class matchmakingServiceShould {

    @Test
    fun `return opponent name when requested`()
    {
        // Arange
        val matchmakingService = MatchmakingService("https://fathomless-fortress-23469.herokuapp.com")

        // Act
        val responseJson = matchmakingService.GetOpponent("Theo")

        val responseUser = Json.decodeFromString<List<User>>(responseJson)

        // Assert
        assertNotNull(responseUser)
    }
}

@Serializable
data class User(val id: Long, var name: String, var email: String) {
    var coin = 0
}
