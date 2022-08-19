package com.team8.Provider

import com.team8.handlers.CreateMatchHandler
import com.team8.handlers.UpdateMatchHandler

object HandlerProvider {

    val createMatch by lazy{
       CreateMatchHandler(UseCaseProvider.getCreateMatch)
    }

    val updateMatch by lazy{
        UpdateMatchHandler(UseCaseProvider.getUpdateMatch, UseCaseProvider.getSaveMatch)
    }
}