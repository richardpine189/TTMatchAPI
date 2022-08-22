package com.team8.Provider

import com.team8.repository.InMemoryMatchRepository

object RepositoryProvider {
    val getRepository by lazy {
        InMemoryMatchRepository(GatewayConfig.localRepositoryPath)
    }
}
