package com.team8.Provider

import com.team8.useCases.CreateMatchUseCase

object UseCaseProvider {
    val getCreateMatch by lazy{
        CreateMatchUseCase(ServiceProvider.getMatchMaking)
    }
}