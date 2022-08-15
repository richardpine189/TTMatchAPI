package com.team8.Provider

import com.team8.handlers.CreateMatchHandler

object HandlerProvider {

    val createMatch by lazy{
       CreateMatchHandler(UseCaseProvider.getCreateMatch)
    }
}