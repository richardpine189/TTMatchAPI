package com.team8.interfaces

import com.team8.domain.boolean

interface ICreateMatchUseCase {
    suspend operator fun invoke(challenger : String) : boolean
}