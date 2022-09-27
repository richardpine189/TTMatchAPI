package com.team8.match.domain.DTO

@kotlinx.serialization.Serializable
data class RoundDTO(
    val id : Int,
    val categories : Array<String>,
    val answers : Array<String>,
    val results : Array<Boolean>,
    val letter : Char, val timeLeft:Int
    )