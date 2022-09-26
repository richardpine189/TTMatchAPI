package com.team8.provider

import com.team8.Provider.UseCaseProvider
import com.team8.match.useCases.getMatchResults.GetMatchResultsHandler
import com.team8.match.useCases.updateMatch.UpdateMatchHandler
import com.team8.match.useCases.createMatch.CreateMatchHandler
import com.team8.match.useCases.getMatchByID.GetSingleMatchHandler
import com.team8.match.useCases.getMatchList.GetMatchListHandler
import com.team8.match.useCases.requestReMatch.ReMatchHandler


object HandlerProvider {


    val getMatch by lazy{
        GetSingleMatchHandler(UseCaseProvider.getMatch)
    }
    val getMatchList by lazy{
        GetMatchListHandler(UseCaseProvider.getMatchList)
    }

    val getMatchResults by lazy{
        GetMatchResultsHandler(UseCaseProvider.getMatchResult)
    }

    val createMatch by lazy{
        CreateMatchHandler(UseCaseProvider.getCreateMatch)
    }

    val updateMatch by lazy{
        UpdateMatchHandler(UseCaseProvider.getUpdateMatch)
    }

    val getReMatch by lazy{
        ReMatchHandler(UseCaseProvider.reMatch)
    }
}