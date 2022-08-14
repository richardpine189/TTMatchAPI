package com.team8

import com.team8.interfaces.IMakeMatch
import com.team8.useCases.CreateMatchUseCase
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
        fun `Setup`() {
            // Arrange
            challenger = "Ricardo"
            opponent = "Theo"
            matchmakingService = mock()
            whenever(matchmakingService.GetOpponent()).thenReturn(opponent)
            createMatchUseCase = CreateMatchUseCase(matchmakingService)
        }
    }

    @Test
    fun `return challenger name inside match`()
    {
        // Act
        val resultMatch = createMatchUseCase(challenger)

        // Assert
        assertEquals(challenger, resultMatch.challenger)
    }

    @Test
    fun `return opponent name inside match`()
    {
        // Act
        val resultMatch = createMatchUseCase(challenger)

        // Assert
        assertEquals(opponent, resultMatch.opponent)
    }
}