package com.team8.match.domain.DTO

import kotlinx.serialization.Serializable

@Serializable
data class RoundDTO(
    val idMatch : Int,
    val categories : Array<String>,
    val answers : Array<String>,
    val results : Array<Boolean>,
    val letter : Char,
    val timeLeft:Int
    )