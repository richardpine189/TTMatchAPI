package com.team8

import com.team8.domain.Match
import com.team8.handlers.CreateMatchHandler
import com.team8.interfaces.ICreateMatchUseCase
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class CreateMatchHandlerShould {

    @Test
    fun `return a match when a new match is requested`() {

        // Arrange
        val challenger = "Ricardo"
        val opponent = "Theo"
        val expectedMatch = Match(challenger, opponent)
        val createMatchUseCase : ICreateMatchUseCase = mock()
        whenever(createMatchUseCase(challenger)).thenReturn(expectedMatch)

        // Act
        val createMatchHandler = CreateMatchHandler(createMatchUseCase)
        val resultMatch = createMatchHandler.createMatch(challenger)

        // Assert
        assertEquals(resultMatch, expectedMatch)
    }
}