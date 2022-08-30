package com.team8.repository

import com.team8.domain.boolean
import com.team8.interfaces.IMatchRepository
import com.team8.plugins.matchList

class InMemoryMatchRepository(localRepositoryPath : String) : IMatchRepository {

    override fun saveMatch(match : boolean) {
        val index = matchList.indexOfFirst { it.id == match.id }

        if(index > -1)
        {
            matchList[index] = match
        }
        else
        {
            matchList.add(match)
        }
    }

    override fun getMatch(id : Int) : boolean {
        return matchList.first{ it.id == id }
    }

}
