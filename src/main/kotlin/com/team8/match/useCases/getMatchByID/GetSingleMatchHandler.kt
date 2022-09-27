package com.team8.match.useCases.getMatchByID

import com.team8.match.interfaces.IHandler
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

class GetSingleMatchHandler(val getMatchUseCase : IGetMatchUseCase) : IHandler {

    override fun routing(a: Application) {
        a.routing {
            route("/GetMatchById") {
                get { getMatch() }
            }
        }
    }

    suspend fun PipelineContext<Unit, ApplicationCall>.getMatch(){
        //val matchIdCandidate = call.request.queryParameters["matchId"]?.toInt()
        val matchIdCandidate = call.parameters["matchId"]!!.toIntOrNull()

        if (matchIdCandidate == null)
        {
            call.respond(HttpStatusCode.BadRequest, "id Match is needed")
        }
        else
        {
            val activeMatchDTO = getMatchUseCase(matchIdCandidate)
            call.respond(activeMatchDTO)
        }
    }


}