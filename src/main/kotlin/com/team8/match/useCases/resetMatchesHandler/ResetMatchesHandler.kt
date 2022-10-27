package com.team8.match.useCases.resetMatchesHandler

import com.team8.match.domain.Match
import com.team8.match.domain.Parsers.MatchParser
import com.team8.match.interfaces.IHandler
import com.team8.plugins.matchList
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

class ResetMatchesHandler(val resetMatchesUseCase: IResetMatchesUseCase) : IHandler {
    override fun routing(a: Application) {
        a.routing {
            route("/resetTest") {
                post { resetMatches() }
            }
        }
    }

    private suspend fun PipelineContext<Unit, ApplicationCall>.resetMatches(){
        resetMatchesUseCase()
    }
}