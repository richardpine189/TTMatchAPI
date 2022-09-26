package com.team8.Provider

import com.team8.match.useCases.getMatchResults.GetMatchResultsUseCase
import com.team8.match.useCases.updateMatch.UpdateMatchUseCase
import com.team8.match.useCases.createMatch.CreateMatchUseCase
import com.team8.match.useCases.getMatchByID.GetMatchUseCase
import com.team8.match.useCases.getMatchList.GetMatchListUseCase
import com.team8.match.useCases.requestReMatch.ReMatchUseCase


object UseCaseProvider {


    val getMatch by lazy{
        GetMatchUseCase(RepositoryProvider.getRepository)
    }
    val getMatchList by lazy {
        GetMatchListUseCase(RepositoryProvider.getRepository)
    }

    val getMatchResult by lazy{
        GetMatchResultsUseCase(RepositoryProvider.getRepository)
    }

    val getCreateMatch by lazy{
        CreateMatchUseCase(ServiceProvider.getMatchMaking, RepositoryProvider.getRepository)
    }

    val getUpdateMatch by lazy{
        UpdateMatchUseCase(RepositoryProvider.getRepository, ServiceProvider.getSetVictory)
    }

    val reMatch by lazy{
        ReMatchUseCase(RepositoryProvider.getRepository)
    }
}