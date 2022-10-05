package com.team8.match.useCases.updateMatch
import com.team8.match.domain.DTO.RoundDTO
import com.team8.match.interfaces.IHandler
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class UpdateMatchHandler(val updateMatchUseCase : IUpdateMatchUseCase) : IHandler {

    override fun routing(a: Application) {
        a.routing {
            route("/updateMatch") {
                post { updateMatch() }
            }
        }
    }

    suspend fun PipelineContext<Unit, ApplicationCall>.updateMatch(){
        val parameters = call.receiveText()

        try{
            val roundDTO = Json.decodeFromString<RoundDTO>(parameters)
            val playerContinueMatch = updateMatchUseCase(roundDTO)
            call.respond(HttpStatusCode.OK, playerContinueMatch)
        }
        catch (ex : SerializationException){
            call.respond(HttpStatusCode.BadRequest, message = "No RoundDTO detected")
        }


    }
}
