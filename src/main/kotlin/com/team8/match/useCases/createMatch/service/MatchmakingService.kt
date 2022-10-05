package com.team8.match.useCases.createMatch.service

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MatchmakingService(val PATH : String) : IMakeMatchService {
    override suspend fun GetOpponent(challengerId : String) : String {
        val client = HttpClient()

        var response : HttpResponse = client.get("$PATH/getOpponent/$challengerId")

        if(response.status == HttpStatusCode.NotFound)
        {
            throw Exception("User service not available.")
        }

        val opponent = Json.decodeFromString<String>(response.body())
        return opponent
    }
}


