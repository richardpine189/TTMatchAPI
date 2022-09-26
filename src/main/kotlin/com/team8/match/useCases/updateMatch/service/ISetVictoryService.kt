package com.team8.match.useCases.updateMatch.service

interface ISetVictoryService {

    suspend fun setVictory(userId : String)
}