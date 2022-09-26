package com.team8.match.useCases.getMatchList

import com.team8.match.interfaces.IHandler
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

class GetMatchListHandler(val getMatcheListUseCase : IGetMatchListUseCase) : IHandler {

    override fun routing(a: Application) {
        a.routing {
            route("/getMatches") {
                get { getMatchList() }
            }
        }
    }

    suspend fun PipelineContext<Unit, ApplicationCall>.getMatchList(){
        val candidate = call.request.queryParameters["challengerUserName"]

        if (candidate == null)
        {
            call.respond(HttpStatusCode.BadRequest, "userName is needed")
        }
        else
        {
            val listMatchDTO = getMatcheListUseCase(candidate)
            call.respond(listMatchDTO)
        }
    }


}
