package com.team8.match.useCases.getMatchResults

import com.team8.match.interfaces.IHandler
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*


class GetMatchResultsHandler(val getMatchResultsUseCase: GetMatchResultsUseCase) : IHandler {
    override fun routing(a: Application) {
        a.routing {
            route("/RoundResults") {
                get { getMatchResults() }
            }
        }
    }

    suspend fun PipelineContext<Unit, ApplicationCall>.getMatchResults(){
        val idMatchCandidate = call.request.queryParameters["matchId"]!!.toIntOrNull()
        val roundIndexCandidate = call.request.queryParameters["round"]!!.toInt()
        if (idMatchCandidate == null || roundIndexCandidate == null)
        {
            call.respond(HttpStatusCode.BadRequest, "idMatch & roundIndex are needed")
        }
        else
        {
            val matchResultsDTO = getMatchResultsUseCase(idMatchCandidate,roundIndexCandidate)


            call.respond(matchResultsDTO)
        }
    }
}