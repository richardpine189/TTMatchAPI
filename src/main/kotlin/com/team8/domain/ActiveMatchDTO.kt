package com.team8.domain

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