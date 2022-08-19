package com.team8.CreateMatch

import com.team8.interfaces.IMakeMatchService
import com.team8.interfaces.ISaveMatchUseCase
import com.team8.useCases.CreateMatchUseCase
import kotlinx.coroutines.test.runTest
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class CreateMatchUseCaseShould {

    companion object {

        @JvmStatic
        private lateinit var challenger : String
        @JvmStatic
        private lateinit var opponent : String
        @JvmStatic
        private lateinit var matchmakingService: IMakeMatchService

        @JvmStatic
        private lateinit var savematchUseCase: ISaveMatchUseCase
        @JvmStatic
        private lateinit var createMatchUseCase: CreateMatchUseCase

        @BeforeClass
        @JvmStatic
        fun `Setup`() = runTest{
            // Arrange
            challenger = "Ricardo"
            opponent = "Theo"
            matchmakingService = mock()
            savematchUseCase = mock()
            whenever(matchmakingService.GetOpponent(challenger)).thenReturn(opponent)
            createMatchUseCase = CreateMatchUseCase(matchmakingService, savematchUseCase)
        }
    }

    @Test
    fun `return challenger name inside match`() = runTest{
        // Act
        val resultMatch = createMatchUseCase(challenger)

        // Assert
        assertEquals(challenger, resultMatch.challenger)
    }

    @Test
    fun `return opponent name inside match`() = runTest{
        // Act
        val resultMatch = createMatchUseCase(challenger)

        // Assert
        assertEquals(opponent, resultMatch.opponent)
    }

    @Test
    fun `save match when its created`()
    {

    }
}