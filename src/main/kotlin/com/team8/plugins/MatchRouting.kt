package com.team8.plugins

import com.team8.Provider.HandlerProvider
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.matchRouting() {
    routing {
        route("/newMatch/{id}") {
            get {
                    val idParameter = call.parameters["id"]
                    if(idParameter == null)
                        call.respond(HttpStatusCode.BadRequest ,"Id is needed")
                    else
                    {
                        val matchCreator = HandlerProvider.createMatch
                        val match = matchCreator.createMatch(idParameter)
                        println(match.opponent)
                        call.respond(HttpStatusCode.Created, "Match Creado")
                    }

            }
        }
    }
}