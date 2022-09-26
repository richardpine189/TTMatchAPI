package com.team8.match.useCases.requestReMatch

import com.team8.match.domain.Parsers.MatchParser
import com.team8.match.interfaces.IHandler
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ReMatchHandler(val reMatchUseCase : IReMatchUseCase) : IHandler{
    override fun routing(a: Application) {
        a.routing{
            route("/setRematch"){
                post { getReMatch()}
            }
        }
    }

    suspend fun PipelineContext<Unit, ApplicationCall>.getReMatch(){
        val playersCandidate = call.receiveText()
        val players: List<String> = Json.decodeFromString(playersCandidate)
        if(players[0] == null || players[1] == null)
        {
            call.respond(HttpStatusCode.BadRequest, "Two players name are needed")
        }
        else
        {
            val challenger = players[0]
            val opponent = players[1]
            val matchDTO = MatchParser.toDto(reMatchUseCase(challenger,opponent))
            call.respond(matchDTO)
        }
    }
}