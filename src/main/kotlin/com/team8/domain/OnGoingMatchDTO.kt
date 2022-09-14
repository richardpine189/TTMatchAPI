package com.team8.domain

import kotlinx.serialization.Serializable

@Serializable
data class OnGoingMatchDTO(
    val idMatch: Int,
    val challengerName : String,
    val opponentName : String,
    val currentRound : Int,
    val isChallengerTurn : Boolean,
    val isMatchFinished : Boolean,
    val matchWinnerStatus : Array<WinnerStatus>
)
