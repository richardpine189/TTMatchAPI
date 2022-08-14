package com.team8.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.matchRouting() {
    routing {
        route("/newMatch") {
            get {

            }
        }
    }
}