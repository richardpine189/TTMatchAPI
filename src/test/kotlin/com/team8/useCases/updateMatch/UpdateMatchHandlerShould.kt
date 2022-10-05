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
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

import org.junit.Test
import kotlin.test.assertEquals

internal class UpdateMatchHandlerShould
{
    @Test
    fun `return true if the player must continue with match`() : Unit = withTestApplication {

        installSerialization()

        val booleanReturn = true
        val cadena : String = "hola"
        val roundDto = RoundDTO(
            0,
            listOf("Categorias","Categorias","Categorias","Categorias","Categorias"),
            listOf("Answers","Answers","Answers","Answers","Answers"),
            listOf(true, false, true, false, true),
            'a',
            60
            )
        val roundEncoded = Json.encodeToString(roundDto)
        val updateMatchUseCase: IUpdateMatchUseCase = mockk()

        val handler = UpdateMatchHandler(updateMatchUseCase)

        coEvery {
            updateMatchUseCase(roundDto)
        } returns booleanReturn

        handler.routing(application)

       with(handleRequest(HttpMethod.Post, "/updateMatch", ) {
           setBody(Json.encodeToString(roundDto))
           addHeader(name = "Content-Type", value = "application/json")
       }) {
           assertEquals(HttpStatusCode.OK, response.status())

       }
    }
    @Test
    fun `respond Bad Request when the deserialization fails`() : Unit = withTestApplication {

        installSerialization()

        val booleanReturn = true
        val cadena : String = "hola"


        val updateMatchUseCase: IUpdateMatchUseCase = mockk()

        val handler = UpdateMatchHandler(updateMatchUseCase)



        handler.routing(application)

        with(handleRequest(HttpMethod.Post, "/updateMatch", ) {
            setBody(Json.encodeToString(cadena))
            addHeader(name = "Content-Type", value = "application/json")
        }) {
            assertEquals(HttpStatusCode.BadRequest, response.status())

        }
    }
    @Test
    fun `return true if the player must continue with match2`() : Unit = withTestApplication{
        installSerialization()

        val booleanReturn = true

        val roundDto = RoundDTO(
            0,
            listOf<String>("Categorias","Categorias","Categorias","Categorias","Categorias"),
            listOf<String>("Answers","Answers","Answers","Answers","Answers"),
            listOf<Boolean>(true, false, true, false, true),
            'a',
            60
        )
        val roundEncoded = Json.encodeToString(roundDto)
        val updateMatchUseCase: IUpdateMatchUseCase = mockk()

        val handler = UpdateMatchHandler(updateMatchUseCase)

        coEvery {
            updateMatchUseCase(roundDto)
        } returns booleanReturn

        handler.routing(application)

        handleRequest(HttpMethod.Post, "/updateMatch", ) {setBody(Json.encodeToString(roundDto))}.apply {
            assertEquals(HttpStatusCode.OK, response.status())
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