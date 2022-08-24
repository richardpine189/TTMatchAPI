package com.team8.Provider

import com.team8.useCases.CreateMatchUseCase
import com.team8.useCases.SaveMatchUseCase
import com.team8.useCases.UpdateMatchUseCase

object UseCaseProvider {
    val getCreateMatch by lazy{
        CreateMatchUseCase(ServiceProvider.getMatchMaking, getSaveMatch)
    }

    val getSaveMatch by lazy{
        SaveMatchUseCase(RepositoryProvider.getRepository)
    }

    val getUpdateMatch by lazy{
        UpdateMatchUseCase(RepositoryProvider.getRepository, ServiceProvider.getSetVictory)
    }


}