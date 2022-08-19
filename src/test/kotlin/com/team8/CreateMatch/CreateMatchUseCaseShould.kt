package com.team8.CreateMatch

import com.team8.interfaces.IMakeMatch
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
        private lateinit var matchmakingService: IMakeMatch

        @JvmStatic
        private lateinit var createMatchUseCase: CreateMatchUseCase

        @BeforeClass
        @JvmStatic
        fun `Setup`() = runTest{
            // Arrange
            challenger = "Ricardo"
            opponent = "Theo"
            matchmakingService = mock()
            whenever(matchmakingService.GetOpponent(challenger)).thenReturn(opponent)
            createMatchUseCase = CreateMatchUseCase(matchmakingService)
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
}