package com.team8.match.domain.DTO

import com.team8.match.domain.WinnerStatus
import kotlinx.serialization.Serializable

@Serializable
data class MatchResultsDTO(
    val currentCategories : Array<String>,
    val challengerAnswers : Array<String>,
    val opponentAnswers : Array<String>,
    val challengerResults : Array<Boolean>,
    val opponentResults : Array<Boolean>,
    val matchStatus: WinnerStatus
)