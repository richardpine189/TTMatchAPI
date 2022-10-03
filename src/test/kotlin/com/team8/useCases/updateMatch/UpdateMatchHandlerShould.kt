package com.team8.useCases.updateMatch

import com.team8.match.domain.DTO.RoundDTO
import com.team8.match.useCases.updateMatch.IUpdateMatchUseCase
import com.team8.match.useCases.updateMatch.UpdateMatchHandler
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

import org.junit.Test

internal class UpdateMatchHandlerShould
{
    //TODO: TESTHandler de POST, error al mockear la respuesta
    @Test
    fun `return a match when a new match is update`() : Unit = withTestApplication {

        installSerialization()

        val booleanReturn = true

        val roundDto = RoundDTO(
            0,
            arrayOf("Categorias","Categorias","Categorias","Categorias","Categorias"),
            arrayOf("Categorias","Categorias","Categorias","Categorias","Categorias"),
            arrayOf(true, false, true, false, true),
            'c',
            60
            )
        val roundEncoded = Json.encodeToString(roundDto)
        val updateMatchUseCase: IUpdateMatchUseCase = mockk()

        coEvery { updateMatchUseCase(roundDto) } returns booleanReturn

        val handler = UpdateMatchHandler(updateMatchUseCase)
        handler.routing(application)

        //val testDecode = Json.decodeFromString<RoundDTO>(roundEncoded)

        //println(testDecode)

        runBlocking {
            println(updateMatchUseCase(roundDto))

            println(updateMatchUseCase(Json.decodeFromString<RoundDTO>(roundEncoded)))
        }


/*
        handleRequest(HttpMethod.Post, "/updateMatch"){setBody(roundEncoded)}.apply {

            TestCase.assertEquals(HttpStatusCode.OK, response.status())

            TestCase.assertEquals(Json.encodeToString(booleanReturn), response.content)

        }*/
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