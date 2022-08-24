package com.team8.Provider

import com.team8.services.MatchmakingService
import com.team8.services.SetVictoryService

object ServiceProvider {

    val getMatchMaking by lazy {
        MatchmakingService(GatewayConfig.userAPI)
    }

    val getSetVictory by lazy {
        SetVictoryService(GatewayConfig.userAPI)
    }
}