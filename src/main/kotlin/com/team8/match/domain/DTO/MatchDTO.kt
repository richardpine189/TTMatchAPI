package com.team8.match.domain.DTO

import com.team8.match.domain.WinnerStatus
import kotlinx.serialization.Serializable

@Serializable
data class MatchDTO(
    val idMatch: Int,
    val challengerName : String,
    val opponentName : String,
    val currentRound : Int,
    val isChallengerTurn : Boolean,
    val isMatchFinished : Boolean,
    val roundResults :Array<WinnerStatus>
)