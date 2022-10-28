package com.team8.match.useCases.createMatch

import com.team8.match.domain.Parsers.MatchParser
import com.team8.match.interfaces.IHandler
import com.team8.match.useCases.exception.FireBaseDBNotAvailableException
import com.team8.match.useCases.exception.ServerNotAvailableException
import com.team8.match.useCases.exception.UserNotFoundException
import com.team8.plugins.matchList
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

class CreateMatchHandler(val CreateMatchUseCase : ICreateMatchUseCase) : IHandler {
    override fun routing(a: Application) {
        a.routing {
            route("/newMatch") {
                get { createMatch() }
            }
        }
    }

    suspend fun PipelineContext<Unit, ApplicationCall>.createMatch(){
        val idParameter = call.request.queryParameters["challengerUserName"]

        if (idParameter == null)
        {
            call.respond(HttpStatusCode.BadRequest, "userName is needed")
        }
        else
        {
            try {
                val match = CreateMatchUseCase(idParameter)
                val matchDto = MatchParser.toDto(match)
                call.respond(matchDto)
            }
            catch (ex: FireBaseDBNotAvailableException)
            {
                call.respond(HttpStatusCode.NotFound, ex.message.toString())
            }
            catch (ex: ServerNotAvailableException)
            {
                call.respond(HttpStatusCode.NotFound, ex.message.toString())
            }
            catch (ex: UserNotFoundException)
            {
                call.respond(HttpStatusCode.NotFound, ex.message.toString())
            }

        }
    }
}