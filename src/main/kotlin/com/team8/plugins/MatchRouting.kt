package com.team8.plugins

import com.team8.Provider.HandlerProvider
import com.team8.domain.Match
import com.team8.domain.MatchDTO
import com.team8.domain.MatchTurn
import com.team8.domain.WinnerStatus
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

var matchList = mutableListOf(
    Match("Ricardo", "Theo", 0),/*
    Match("Theo", "Ricardo", 1),
    Match("Romina", "Ricardo", 2)*/
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
        route("/getMatches") {
            get("/{userName}") {
                val candidate = call.parameters["userName"]
                val listMatch = matchList.filter{ it.challenger == candidate || it.opponent == candidate}
                println(candidate)
                val listMatchDTO = mutableListOf<MatchDTO>()
                listMatch.forEach{ listMatchDTO.add(MatchDTO(
                        it.id,
                        it.challenger,
                        it.opponent,
                        it.currentRound,
                        (it.matchTurn == MatchTurn.Challenger),
                        false, // HARDCODED ENDROUND COMPROBATION
                        arrayOf(it.rounds[0].winner, it.rounds[1].winner, it.rounds[2].winner)

                    )
                )
                }

                println(listMatchDTO[0].roundResults[0])
                println(listMatchDTO[0].roundResults[1])
                println(listMatchDTO[0].roundResults[2])
                call.respond(listMatchDTO)
            }
        }
    }
}