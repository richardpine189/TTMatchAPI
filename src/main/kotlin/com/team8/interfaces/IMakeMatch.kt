package com.team8.interfaces

interface IMakeMatch {
    suspend fun GetOpponent(challengerId : String) : String
}