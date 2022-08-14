package com.team8.services

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class MatchmakingService(val PATH : String) {
    fun GetOpponent(userName : String) : String {

        val (request, response, result) = "$PATH/user"
            .httpGet().response()

        return String(response.data)
    }
}

