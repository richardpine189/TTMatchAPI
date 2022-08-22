package com.team8.interfaces

import com.team8.domain.Match

interface IMatchRepository {
    fun saveMatch(match : Match)
    fun getMatch(id : Int) : Match
}