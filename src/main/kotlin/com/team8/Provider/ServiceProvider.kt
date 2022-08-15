package com.team8.Provider

import com.team8.services.MatchmakingService

object ServiceProvider {

    val getMatchMaking by lazy {
        MatchmakingService(GatewayConfig.userAPI)
    }
}