package com.team8.plugins

import com.team8.Provider.HandlerProvider
import com.team8.domain.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

var matchList = mutableListOf<Match>()
    /*Match("Ricardo", "Theo", 0),
    Match("Theo", "Ricardo", 1),
    Match("Romina", "Ricardo", 2)*/

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
                val listMatchDTO = mutableListOf<OnGoingMatchDTO>()
                listMatch.forEach{ listMatchDTO.add(
                    OnGoingMatchDTO(
                        it.id,
                        it.challenger,
                        it.opponent,
                        it.currentRound,
                        (it.matchTurn == MatchTurn.Challenger),
                        it.currentRound == it.rounds.size,
                        arrayOf(it.rounds[0].winner,it.rounds[1].winner,it.rounds[2].winner)
                    )
                )
                }
                call.respond(listMatchDTO)
            }
        }

        route("/GetMatchById") {
            get("/{matchId}") {
                val idCandidate = call.parameters["matchId"]!!.toIntOrNull()
                val match = matchList.first{ it.id == idCandidate}
                val activeMatchDTO = ActiveMatchDTO(
                    match.challenger,
                    match.opponent,
                    match.currentRound,
                    match.rounds[match.currentRound].letter!!,
                    match.rounds[match.currentRound].timeLeft!!,
                    match.rounds[match.currentRound].categoryNames,

                )
                call.respond(activeMatchDTO)
            }
        }

        route("/RoundResults") {
            get() {
                val id = call.request.queryParameters["matchId"]!!.toIntOrNull()
                val roundIndex = call.request.queryParameters["round"]!!.toInt()
                val match = matchList.first{ it.id == id}
                val matchResultsDTO = MatchResultsDTO(
                    match.rounds[roundIndex].categoryNames,
                    match.rounds[roundIndex].challengerAnswers,
                    match.rounds[roundIndex].opponentAnswers,
                    match.rounds[roundIndex].challengerResults,
                    match.rounds[roundIndex].opponentResults
                )
                call.respond(matchResultsDTO)
            }
        }
        }
    }
