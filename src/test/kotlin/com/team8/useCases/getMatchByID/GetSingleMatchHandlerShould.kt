package com.team8.useCases.getMatchByID

import com.team8.match.domain.DTO.ActiveMatchDTO
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
import kotlin.test.BeforeTest

internal class GetSingleMatchHandlerShould
{
    lateinit var activeMatchDto : ActiveMatchDTO
    lateinit var getMatch : IGetMatchUseCase

    @BeforeTest
    fun initialize() : Unit = withTestApplication{
        val match = Match("Ricardo", "test", 5)
        activeMatchDto = MatchParser.toActiveMatchDto(match)

        getMatch = mockk()
        coEvery { getMatch(5) } returns activeMatchDto
        coEvery { getMatch(-1) } throws Exception("ID can't be less than zero")
    }

    @Test
    fun `return an activeMatch when a matchID is sent`() : Unit = withTestApplication{

        installSerialization()
        val handler = GetSingleMatchHandler(getMatch)
        handler.routing(application)

        handleRequest(HttpMethod.Get, "/GetMatchById/${5}").apply {
            TestCase.assertEquals(HttpStatusCode.OK, response.status())
            TestCase.assertEquals(Json.encodeToString(activeMatchDto), response.content)
        }
    }

    @Test
    fun `return Bad Request when no matchID is sent`() : Unit = withTestApplication {

        installSerialization()
        val handler = GetSingleMatchHandler(getMatch)
        handler.routing(application)

        val requestMatchId : Int? = null

        handleRequest(HttpMethod.Get, "/GetMatchById/${requestMatchId}").apply {
            TestCase.assertEquals(HttpStatusCode.BadRequest, response.status())
        }
    }

    @Test
    fun `return a Bad Request when matchId es less than zero`() : Unit = withTestApplication {

        installSerialization()
        val handler = GetSingleMatchHandler(getMatch)
        handler.routing(application)

        handleRequest(HttpMethod.Get, "/GetMatchById/${-1}").apply {
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