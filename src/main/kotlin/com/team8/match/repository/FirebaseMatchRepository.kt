package com.team8.match.repository

import com.team8.match.domain.Match
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FirebaseMatchRepository(val repositoryPath : String) : IMatchRepository {
    override suspend fun saveMatch(match: Match) {

        if(match.id == -1)
        {
            val newId = GetNewId()
            match.id = newId
        }

        val serializedData = Json.encodeToString(match)

        val client = HttpClient()

        val response = client.put("$repositoryPath/matches/${match.id}.json")
        {
            setBody(serializedData)
        }

        if(response.status == HttpStatusCode.NotFound)
        {
            throw java.lang.Exception("The service is not available")
        }
    }

    override suspend fun getMatch(id: Int): Match {

        val matchList = getAllMatches()
        return matchList.first { it.id == id }
    }

    override suspend fun getMatchListByUserId(userId: String): List<Match> {

        val matchList = getAllMatches()
        return matchList.filter { it.challenger == userId || it.opponent == userId }
    }

    override suspend fun resetRepository() {
        val client = HttpClient()

        var responseForUpdateIndex: HttpResponse = client.put("$repositoryPath/matchesIndex.json") {
            contentType(ContentType.Application.Json)
            setBody("{\"index\": 0}")
        }

        if(responseForUpdateIndex.status == HttpStatusCode.NotFound)
        {
            throw Exception("Match database not available.")
        }

        var response: HttpResponse = client.put("$repositoryPath/matches.json") {
            contentType(ContentType.Application.Json)
            setBody("{}")
        }

        if(response.status == HttpStatusCode.NotFound)
        {
            throw Exception("Match database not available.")
        }

        return response.body()
    }

    private suspend fun getAllMatches() : List<Match>{
        val client = HttpClient()

        var response : HttpResponse = client.get("$repositoryPath/matches.json")

        if(response.status == HttpStatusCode.NotFound)
        {
            throw Exception("Match database not available.")
        }

        if(response.bodyAsText() == "null")
        {
            return emptyList()
        }

        val matchList = Json.decodeFromString<List<Match>>(response.body())

        return matchList
    }

    private suspend fun GetNewId(): Int {
        val client = HttpClient()

        var response : HttpResponse = client.get("$repositoryPath/matchesIndex/index.json")


        if(response.status == HttpStatusCode.NotFound)
        {
            throw Exception("Match database not available.")
        }

        var actualIndex: Int = response.body()
        actualIndex++
        var responseForUpdateIndex: HttpResponse = client.put("$repositoryPath/matchesIndex.json") {
            contentType(ContentType.Application.Json)
            setBody("{\"index\": ${actualIndex}}")
        }

        if(responseForUpdateIndex.status == HttpStatusCode.NotFound)
        {
            throw Exception("Match database not available.")
        }

        return response.body()
    }
}