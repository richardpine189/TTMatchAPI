package com.team8.useCases.updateMatch

import com.team8.match.domain.DTO.RoundDTO
import com.team8.match.domain.Match
import com.team8.match.domain.WinnerStatus
import com.team8.match.repository.IMatchRepository
import com.team8.match.useCases.updateMatch.UpdateMatchUseCase
import com.team8.match.useCases.updateMatch.service.ISetVictoryService
import io.ktor.server.testing.*
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class UpdateMatchUseCaseShould
{
    val challenger = "Ricardo"
    val opponent = "Theo"
    val matchId = 3
    val roundDto = RoundDTO(
        matchId,
        listOf("Categorias","Categorias","Categorias","Categorias","Categorias"),
        listOf("Answers","Answers","Answers","Answers","Answers"),
        listOf(true, false, true, false, true),
        'a',
        60
    )

    val service : ISetVictoryService = mockk()
    val repository : IMatchRepository = mockk()
    val match : Match = mockk()
    lateinit var updateMatchUseCase : UpdateMatchUseCase

    @BeforeTest
    fun initialize() = runTest {
        updateMatchUseCase = UpdateMatchUseCase(repository, service)

        every { match.updateMatch(roundDto)} returns Unit
        every { match.matchHasWinner() } returns false
        every { match.roundHasEnded() } returns false
        every { match.winner } returns WinnerStatus.Challenger
        every { match.challenger } returns challenger
        every { match.opponent } returns opponent

        coEvery { service.setVictory(any()) } returns Unit

        coEvery { repository.getMatch(matchId) } returns match
        coEvery { repository.saveMatch(match) } returns Unit
    }

    @Test
    fun `Get match from repository`() : Unit = runTest{
        // Act
        updateMatchUseCase(roundDto)

        // Assert
        coVerify{repository.getMatch(matchId)}
    }

    @Test
    fun `Update match is called`() : Unit = runTest {
        // Act
        updateMatchUseCase(roundDto)

        // Assert
        verify{match.updateMatch(roundDto)}
    }

    @Test
    fun `Save match to repository`() : Unit = runTest {
        // Act
        updateMatchUseCase(roundDto)

        // Assert
        coVerify{repository.saveMatch(match)}
    }

    @Test
    fun `If match has winner return true`() : Unit = runTest {
        // Arrange
        every { match.matchHasWinner() } returns true

        // Act
        // Assert
        assertTrue { updateMatchUseCase(roundDto) }
    }

    @Test
    fun `Call victory service if challenger won`() : Unit = runTest {
        // Arrange
        every { match.matchHasWinner() } returns true

        // Act
        updateMatchUseCase(roundDto)

        // Assert
        coVerify { service.setVictory(challenger) }
    }

    @Test
    fun `Call victory service if opponent won`() : Unit = runTest {
        // Arrange
        every { match.matchHasWinner() } returns true
        every { match.winner } returns WinnerStatus.Opponent

        // Act
        updateMatchUseCase(roundDto)

        // Assert
        coVerify { service.setVictory(opponent) }
    }

    @Test
    fun `Verify setVictory isn't called when match not finished`() : Unit = runTest {
        // Act
        updateMatchUseCase(roundDto)

        // Assert
        coVerify(inverse = true){ service.setVictory(any()) }
    }

    @Test
    fun `Verify setVictory isn't called when match is draw`() : Unit = runTest {
        // Arrange
        every { match.winner } returns WinnerStatus.Draw

        // Act
        updateMatchUseCase(roundDto)

        // Assert
        coVerify(inverse = true){ service.setVictory(any()) }
    }

    @Test
    fun `Return true if round has ended`() : Unit = runTest {
        // Arrange
        every { match.roundHasEnded() } returns true

        // Act
        // Assert
        assertTrue { updateMatchUseCase(roundDto) }
    }

    @Test
    fun `Return false if round hasn't ended`() : Unit = runTest {
        // Act
        // Assert
        assertFalse { updateMatchUseCase(roundDto) }
    }
}
