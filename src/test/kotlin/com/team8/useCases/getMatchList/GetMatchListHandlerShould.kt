package com.team8.useCases.getMatchList

import com.team8.match.domain.DTO.OnGoingMatchDTO
import com.team8.match.domain.Match
import com.team8.match.domain.Parsers.MatchParser
import com.team8.match.useCases.getMatchByID.GetSingleMatchHandler
import com.team8.match.useCases.getMatchList.GetMatchListHandler
import com.team8.match.useCases.getMatchList.IGetMatchListUseCase
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

internal class GetMatchListHandlerShould
{

    @Test
    fun `return OK response when request is made`() : Unit = withTestApplication(){

        // Arrange
        installSerialization()

        var matchList = mutableListOf<Match>(
            Match("Ricardo", "Theo", 0),
            Match("Theo", "Ricardo", 1),
            Match("Romina", "Ricardo", 2)
        )

        val onGoingMatchList = mutableListOf<OnGoingMatchDTO>()

        matchList.forEach{
            onGoingMatchList.add(
                MatchParser.toOngoingMatchDto(it)
            )
        }

        val getMatchList : IGetMatchListUseCase = mockk()
        coEvery { getMatchList("Ricardo") } returns onGoingMatchList

        val handler = GetMatchListHandler(getMatchList)
        handler.routing(application)

        // Act
        handleRequest(HttpMethod.Get, "/getMatches?userName=Ricardo").apply{
            // Assert
            TestCase.assertEquals(HttpStatusCode.OK, response.status())
            TestCase.assertEquals(Json.encodeToString(onGoingMatchList), response.content)
        }
    }

    @Test
    fun `bad request when no userName`() : Unit = withTestApplication {
        val userName = null

        // Act
        handleRequest(HttpMethod.Get, "/getMatches?userName=${userName}").apply{
            // Assert
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