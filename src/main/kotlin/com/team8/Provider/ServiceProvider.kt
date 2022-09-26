package com.team8.Provider

import com.team8.match.useCases.createMatch.service.MatchmakingService
import com.team8.match.useCases.updateMatch.service.SetVictoryService

object ServiceProvider {

    val getMatchMaking by lazy {
        MatchmakingService(GatewayConfig.userAPI)
    }

    val getSetVictory by lazy {
        SetVictoryService(GatewayConfig.userAPI)
    }
}