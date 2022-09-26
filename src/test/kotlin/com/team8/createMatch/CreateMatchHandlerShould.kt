package com.team8.createMatch

import com.team8.match.domain.Match
import com.team8.match.domain.Parsers.MatchParser
import com.team8.match.useCases.createMatch.CreateMatchHandler
import com.team8.match.useCases.createMatch.ICreateMatchUseCase
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test

class CreateMatchHandlerShould {
    @Test
    fun `return a match when a new match is requested`() : Unit = withTestApplication{

        installSerialization()

        val user = "Ricardo"

        // El id es 0 xq por ahora el id se asigna como el tamaño del matchList global (que es 0 en un test)
        val match = Match(user, "test", 0)
        val matchDto = MatchParser.toDto(match)

        val createMatchUseCase : ICreateMatchUseCase = mockk()
        coEvery { createMatchUseCase(user) } returns match
        val handler = CreateMatchHandler(createMatchUseCase)
        handler.routing(application)

        handleRequest(HttpMethod.Get, "/newMatch?challengerUserName=${user}").apply {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(Json.encodeToString(matchDto), response.content)
        }
    }

    @Test
    fun `return Bad Request when no userName is sent`() : Unit = withTestApplication {
        installSerialization()

        val createMatchUseCase : ICreateMatchUseCase = mockk()
        val handler = CreateMatchHandler(createMatchUseCase)
        handler.routing(application)

        handleRequest(HttpMethod.Get, "/newMatch").apply {
            assertEquals(HttpStatusCode.BadRequest, response.status())
        }
    }

    @Test
    fun `return NotFound when the user services is down`() : Unit = withTestApplication {
        installSerialization()

        val createMatchUseCase : ICreateMatchUseCase = mockk()
        val handler = CreateMatchHandler(createMatchUseCase)
        handler.routing(application)

        handleRequest(HttpMethod.Get, "/newMatch/?challengerUserName=Theo").apply {
            assertEquals(HttpStatusCode.NotFound, response.status())
        }
    }

    private fun TestApplicationEngine.installSerialization() {
        application.install(ContentNegotiation) {
            json(
                Json{
                    ignoreUnknownKeys = true
                }
            )
        }
    }
}