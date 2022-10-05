package com.team8.repository

import com.team8.match.domain.Match
import com.team8.match.repository.FirebaseMatchRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class FirebaseMatchRespositoryShould {
    @Test
    fun `test`() = runTest {
        val repo = FirebaseMatchRepository("https://topictwister-142c9-default-rtdb.firebaseio.com")

        val match = Match("test", "test", 3)

        val matches = repo.saveMatch(match)

        println(matches)
    }

//    @Test
//    fun `getMatches`() = runTest {
//        val repo = FirebaseMatchRepository("https://topictwister-142c9-default-rtdb.firebaseio.com")
//
//        val matches = repo.getAllMatches()
//
//        println(matches)
//    }
}