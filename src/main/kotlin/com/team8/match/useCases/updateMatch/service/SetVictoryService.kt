package com.team8.match.useCases.updateMatch.service

import io.ktor.client.*
import io.ktor.client.request.*

class SetVictoryService(val PATH: String) : ISetVictoryService {

    override suspend fun setVictory(userId: String) {
        val client = HttpClient()
        val response = client.patch("$PATH/addVictory") {setBody(userId)}
    }
}