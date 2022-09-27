package com.team8.createMatch


import com.team8.match.domain.Match
import com.team8.match.useCases.createMatch.service.IMakeMatchService
import com.team8.match.repository.IMatchRepository
import com.team8.match.useCases.createMatch.CreateMatchUseCase
import kotlinx.coroutines.test.runTest
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CreateMatchUseCaseShould {

    private lateinit var resultMatch: Match
    companion object {

        @JvmStatic
        private lateinit var challenger : String
        @JvmStatic
        private lateinit var opponent : String
        @JvmStatic
        private lateinit var matchmakingService: IMakeMatchService

        @JvmStatic
        private lateinit var matchRepository: IMatchRepository
        @JvmStatic
        private lateinit var createMatchUseCase: CreateMatchUseCase


        @BeforeClass
        @JvmStatic
        fun `Setup`() = runTest{
            // Arrange
            challenger = "Ricardo"
            opponent = "\"Theo\""
            matchmakingService = mock()
            matchRepository = mock()
            whenever(matchmakingService.GetOpponent(challenger)).thenReturn(opponent)
            createMatchUseCase = CreateMatchUseCase(matchmakingService, matchRepository)
        }
    }

    @Test
    fun `return challenger name inside match`() = runTest{
        // Act
        CreateMatch()

        // Assert
        assertEquals(challenger, resultMatch.challenger)
    }

    /*@Test
    fun `return opponent name inside match`() = runTest{
        // Act
        CreateMatch()

        // Assert
        assertEquals(opponent, resultMatch.opponent)
    }*/

    @Test
    fun `return Match when is request CreateMatch`() = runTest{

        //Act
        CreateMatch()

        //Assert
        assertTrue(resultMatch is Match)
    }
    @Test
    fun `save match when its created`() = runTest{

        //Arrange
        CreateMatch()

        verify(matchRepository,times(1)).saveMatch(resultMatch)
    }

    private suspend fun CreateMatch()
    {
        resultMatch = createMatchUseCase(challenger)
    }
}