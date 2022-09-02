package com.team8.interfaces

import com.team8.domain.Match

interface ICreateMatchUseCase {
    suspend operator fun invoke(challenger : String) : Match
}