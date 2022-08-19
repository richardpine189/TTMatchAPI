package com.team8.interfaces

interface IMakeMatchService {
    suspend fun GetOpponent(challengerId : String) : String
}