package com.team8.handlers

import com.team8.domain.Round
import com.team8.domain.RoundDTO
import com.team8.domain.RoundStatus
import com.team8.interfaces.IHandler
import com.team8.interfaces.ISaveMatchUseCase
import com.team8.interfaces.IUpdateMatchUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.text.get

class UpdateMatchHandler(val updateMatchUseCase : IUpdateMatchUseCase, val saveMatchUseCase: ISaveMatchUseCase) : IHandler {

    override fun routing(a: Application) {
        a.routing {
            route("/updateMatch") {
                post { updateMatch() }
            }
        }
    }

    suspend fun PipelineContext<Unit, ApplicationCall>.updateMatch(){
        val parameters = call.receiveText()
        val roundDTO = Json.decodeFromString<RoundDTO>(parameters)
        call.respond(HttpStatusCode.OK, updateMatchUseCase(roundDTO))
    }
}
