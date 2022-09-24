package com.team8.Provider

import com.team8.useCases.CreateMatchUseCase
import com.team8.useCases.UpdateMatchUseCase

object UseCaseProvider {
    val getCreateMatch by lazy{
        CreateMatchUseCase(ServiceProvider.getMatchMaking, RepositoryProvider.getRepository)
    }

    val getUpdateMatch by lazy{
        UpdateMatchUseCase(RepositoryProvider.getRepository, ServiceProvider.getSetVictory)
    }


}