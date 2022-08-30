package com.team8.plugins

import com.team8.Provider.HandlerProvider
import com.team8.domain.boolean
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

var matchList = mutableListOf<boolean>(
    boolean("Ricardo", "Theo", 0),
    //Match("Romina", "Ricardo", 1)
)

fun Application.matchRouting() {
    val createMathHandler = HandlerProvider.createMatch
    createMathHandler.routing(this)

    val updateMatchHandler = HandlerProvider.updateMatch
    updateMatchHandler.routing(this)

    routing {
        route("testGet") {
            get {
                call.respond(matchList)
            }
        }
    }
}