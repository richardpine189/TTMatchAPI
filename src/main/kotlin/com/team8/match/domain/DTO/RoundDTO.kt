package com.team8.match.domain.DTO

import kotlinx.serialization.Serializable

@Serializable
data class RoundDTO(
    val idMatch : Int,
    val categories : List<String>,
    val answers : List<String>,
    val results : List<Boolean>,
    val letter : Char,
    val timeLeft:Int
)