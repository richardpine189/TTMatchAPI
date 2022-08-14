package com.team8.handlers

import com.team8.domain.Match
import com.team8.interfaces.ICreateMatchUseCase

class CreateMatchHandler(val CreateMatchUseCase : ICreateMatchUseCase) {
    fun createMatch(challenger: String) : Match {
        return CreateMatchUseCase(challenger)
    }
}