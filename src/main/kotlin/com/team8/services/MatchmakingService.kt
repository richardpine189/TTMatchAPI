package com.team8.services
import com.team8.interfaces.IMakeMatchService
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class MatchmakingService(val PATH : String) : IMakeMatchService {
    override suspend fun GetOpponent(challengerId : String) : String {
        val client = HttpClient()
        val response = client.get("$PATH/getOpponent/$challengerId")
        return response.bodyAsText()
    }
}


