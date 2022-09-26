package com.team8.match.domain.DTO

import kotlinx.serialization.Serializable

@Serializable
data class ActiveMatchDTO(
    val challengerName: String,
    val opponentName: String,
    val currentRound: Int,
    val currentLetter: Char,
    val currentTime: Int,
    val currentCategories: Array<String>
)