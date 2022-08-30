package com.team8.handlers

import com.team8.Provider.UseCaseProvider
import com.team8.interfaces.ICreateMatchUseCase
import com.team8.interfaces.IHandler
import com.team8.plugins.matchList
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

class CreateMatchHandler(val CreateMatchUseCase : ICreateMatchUseCase) : IHandler {
    override fun routing(a: Application) {
        a.routing {
            route("/newMatch/{challengerUserName}") {
                get { createMatch() }
            }
        }
    }

    suspend fun PipelineContext<Unit, ApplicationCall>.createMatch(){
        val idParameter = call.parameters["challengerUserName"]
        if (idParameter == null)
            call.respond(HttpStatusCode.BadRequest, "userName is needed")
        else {
            val matchCreator = UseCaseProvider.getCreateMatch
            val match = matchCreator(idParameter)
            match.id = matchList.size
            matchList.add(match)
            //call.respond(HttpStatusCode.Created, "Match Creado")
            call.respond(match)
        }
    }


}