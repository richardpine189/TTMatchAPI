package com.team8.match.useCases.createMatch.service

interface IMakeMatchService {
    suspend fun GetOpponent(challengerId : String) : String
}