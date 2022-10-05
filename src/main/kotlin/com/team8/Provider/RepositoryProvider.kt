package com.team8.Provider

import com.team8.match.repository.FirebaseMatchRepository
import com.team8.match.repository.InMemoryMatchRepository

object RepositoryProvider {
    val getRepository by lazy {
//        InMemoryMatchRepository(GatewayConfig.localRepositoryPath)
        FirebaseMatchRepository("https://topictwister-142c9-default-rtdb.firebaseio.com")
    }
}
