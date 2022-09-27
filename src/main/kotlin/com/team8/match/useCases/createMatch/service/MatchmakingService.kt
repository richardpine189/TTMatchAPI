package com.team8.match.useCases.createMatch.service

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class MatchmakingService(val PATH : String) : IMakeMatchService {
    override suspend fun GetOpponent(challengerId : String) : String {
        val client = HttpClient()
        var response : HttpResponse

        try{
            response = client.get("$PATH/getOpponent/$challengerId")
            return response.body()
        }
        catch (ex : Exception){
            throw Exception("The service is not available")
        }
    }
}


