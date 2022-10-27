package com.team8.plugins

import com.team8.match.domain.*
import com.team8.match.domain.DTO.ActiveMatchDTO
import com.team8.Provider.HandlerProvider
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

var matchList = mutableListOf<Match>()
/*
    Match("Ricardo", "Theo", 0),
    Match("Theo", "Ricardo", 1),
    Match("Romina", "Ricardo", 2))
*/
fun Application.matchRouting() {
    val createMathHandler = HandlerProvider.createMatch
    createMathHandler.routing(this)

    val updateMatchHandler = HandlerProvider.updateMatch
    updateMatchHandler.routing(this)

    val getMatchListByPlayerID = HandlerProvider.getMatchList
    getMatchListByPlayerID.routing(this)

    val getMatchById = HandlerProvider.getMatch
    getMatchById.routing(this)

    val getMatchResults = HandlerProvider.getMatchResults
    getMatchResults.routing(this)

    val reMatch = HandlerProvider.getReMatch
    reMatch.routing(this)

    val resetMatchRepository = HandlerProvider.resetMatchesRepository
    resetMatchRepository.routing(this)

    routing {

        get("/") {
            call.respondText("matchAPI is running!!!")
        }

        route("testGet") {
            get {
                call.respond(matchList)
            }
        }
        route("/testGetMatchById") {
            get("/{matchId}") {
                val idCandidate = call.parameters["matchId"]!!.toIntOrNull()
                val match = matchList.first{ it.id == idCandidate}
                val activeMatchDTO = ActiveMatchDTO(
                    match.challenger,
                    match.opponent,
                    match.currentRound,
                    //match.rounds[match.currentRound].letter,
                    's',
                    10,
                    arrayOf("hola","mundo","test","de","endpoint")
                    //match.rounds[match.currentRound].timeLeft!!,
                    //match.rounds[match.currentRound].categoryNames,
                    )
                call.respond(activeMatchDTO)
            }
        }
    }
}