package com.team8.useCases.getMatchByID

import com.team8.match.domain.Match
import com.team8.match.domain.Parsers.MatchParser
import com.team8.match.useCases.createMatch.CreateMatchHandler
import com.team8.match.useCases.createMatch.ICreateMatchUseCase
import com.team8.match.useCases.getMatchByID.GetSingleMatchHandler
import com.team8.match.useCases.getMatchByID.IGetMatchUseCase
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test

internal class GetSingleMatchHandlerShould
{
    @Test
    fun `return an activeMatch when a matchID is sent`() : Unit = withTestApplication{

        installSerialization()

        val user = "Ricardo"
        val matchID = 0;

        val match = Match(user, "test", matchID)
        val activeMatchDto = MatchParser.toActiveMatchDto(match)

        val getMatch : IGetMatchUseCase = mockk()
        coEvery { getMatch(matchID) } returns activeMatchDto
        val handler = GetSingleMatchHandler(getMatch)
        handler.routing(application)

        handleRequest(HttpMethod.Get, "/GetMatchById/${matchID}").apply {

            TestCase.assertEquals(HttpStatusCode.OK, response.status())
            TestCase.assertEquals(Json.encodeToString(activeMatchDto), response.content)
        }
    }

    @Test
    fun `return Bad Request when no matchID is sent`() : Unit = withTestApplication {

        installSerialization()
        val matchID : Int? = null
        val getMatch : IGetMatchUseCase = mockk()
        val handler = GetSingleMatchHandler(getMatch)
        handler.routing(application)

        handleRequest(HttpMethod.Get, "/GetMatchById/${matchID}").apply {
            TestCase.assertEquals(HttpStatusCode.BadRequest, response.status())
        }
    }

    @Test
    fun `return a Bad Request when matchId es less than zero`() : Unit = withTestApplication {
        installSerialization()

        val matchID : Int = 5
        val getMatch : IGetMatchUseCase = mockk()
        val handler = GetSingleMatchHandler(getMatch)

        handler.routing(application)

        coEvery { getMatch(0 - matchID) } throws Exception("msg")

        handleRequest(HttpMethod.Get, "/GetMatchById/${matchID}").apply {
            TestCase.assertEquals(HttpStatusCode.BadRequest, response.status())
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