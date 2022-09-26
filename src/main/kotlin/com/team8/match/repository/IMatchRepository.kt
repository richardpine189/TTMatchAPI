package com.team8.match.repository

import com.team8.match.domain.Match

interface IMatchRepository {
    fun saveMatch(match : Match)
    fun getMatch(id : Int) : Match
    fun getMatchListByUserId(userId : String) : List<Match>
}