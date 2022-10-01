package com.team8.match.useCases.createMatch.service

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MatchmakingService(val PATH : String) : IMakeMatchService {
    override suspend fun GetOpponent(challengerId : String) : String {
        val client = HttpClient()
        var response : HttpResponse

        try{
            response = client.get("$PATH/getOpponent/$challengerId")
            val opponent = Json.decodeFromString<String>(response.body())
            return opponent
        }
        catch (ex : Exception){
            throw Exception("The service is not available")
        }
    }
}


