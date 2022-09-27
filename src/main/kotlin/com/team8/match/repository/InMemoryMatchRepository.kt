package com.team8.match.repository

import com.team8.match.domain.Match
import com.team8.plugins.matchList

class InMemoryMatchRepository(localRepositoryPath : String) : IMatchRepository {

    override fun saveMatch(match : Match) {
        val index = matchList.indexOfFirst { it.id == match.id }

        if(index > -1)
        {
            matchList[index] = match
        }
        else
        {
            match.id = matchList.size
            matchList.add(match)
        }
    }

    override fun getMatch(id : Int) : Match {
        return matchList.first{ it.id == id }
    }

    override fun getMatchListByUserId(userId : String) : List<Match> {
        return matchList.filter { it.challenger == userId || it.opponent == userId }
    }

}
