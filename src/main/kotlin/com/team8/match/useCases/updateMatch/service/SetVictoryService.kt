package com.team8.match.useCases.updateMatch.service

import io.ktor.client.*
import io.ktor.client.request.*
import io.netty.handler.codec.http2.Http2Exception
import java.lang.Exception

class SetVictoryService(val PATH: String) : ISetVictoryService {

    override suspend fun setVictory(userId: String) {
        val client = HttpClient()

        try {
            val response = client.patch("$PATH/addVictory") {setBody(userId)}
        }
        catch (ex: Exception)
        {
            throw Exception("The service is not available")
        }

    }
}