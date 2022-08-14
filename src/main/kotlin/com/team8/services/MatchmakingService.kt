package com.team8.services

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class MatchmakingService(val PATH : String) {
    suspend fun GetOpponent(challengerId : String) : String {
        val client = HttpClient()
        val response = client.get("$PATH/user/getOpponent/$challengerId")
        return response.bodyAsText()
        /*
        val (request, response, result) = "$PATH/user"
            .httpGet().response()

        return String(response.data)*/
    }
}


