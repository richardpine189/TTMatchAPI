package com.team8.Provider

import com.team8.repository.MatchRepository

object RepositoryProvider {
    val getRepository by lazy {
        MatchRepository(GatewayConfig.localRepositoryPath)
    }
}
