package com.team8.services

import com.team8.interfaces.ISetVictoryService
import io.ktor.client.*
import io.ktor.client.request.*

class SetVictoryService(val PATH: String) : ISetVictoryService {

    override suspend fun setVictory(userId: String) {
        val client = HttpClient()
        val response = client.patch("$PATH/addVictory")
        //FALTA MANDAR USER
    }
}