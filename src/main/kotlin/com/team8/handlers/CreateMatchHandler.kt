package com.team8.handlers

import com.team8.Provider.HandlerProvider
import com.team8.Provider.UseCaseProvider
import com.team8.domain.Match
import com.team8.interfaces.ICreateMatchUseCase
import com.team8.interfaces.IHandler
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

class CreateMatchHandler(val CreateMatchUseCase : ICreateMatchUseCase) : IHandler {
    override fun routing(a: Application) {
        a.routing {
            route("/newMatch/{id}") {
                get { createMatch() }
            }
        }
    }

    suspend fun PipelineContext<Unit, ApplicationCall>.createMatch(){
        val idParameter = call.parameters["id"]
        if (idParameter == null)
            call.respond(HttpStatusCode.BadRequest, "Id is needed")
        else {
            val matchCreator = UseCaseProvider.getCreateMatch
            val match = matchCreator(idParameter)

            println(match.opponent)
            //call.respond(HttpStatusCode.Created, "Match Creado")
            call.respond(match)
        }
    }


}