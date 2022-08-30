package com.team8.interfaces

import com.team8.domain.boolean

interface IMatchRepository {
    fun saveMatch(match : boolean)
    fun getMatch(id : Int) : boolean
}