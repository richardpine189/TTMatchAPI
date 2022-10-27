package com.team8.match.repository

import com.team8.match.domain.Match

interface IMatchRepository {
    suspend fun saveMatch(match : Match)

    suspend fun getMatch(id : Int) : Match

    suspend fun getMatchListByUserId(userId : String) : List<Match>

    suspend fun resetRepository()
}