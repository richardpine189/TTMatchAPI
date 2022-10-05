package com.team8.match.useCases.updateMatch.service

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.netty.handler.codec.http2.Http2Exception
import java.lang.Exception

class SetVictoryService(val PATH: String) : ISetVictoryService {

    override suspend fun setVictory(userId: String) {
        val client = HttpClient()

        val response = client.patch("$PATH/addVictory") {setBody(userId)}

        if(response.status == HttpStatusCode.NotFound)
        {
            throw Exception("The service is not available")
        }
    }
}