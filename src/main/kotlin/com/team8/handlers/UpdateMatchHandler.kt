package com.team8.handlers

import com.team8.domain.Round
import com.team8.interfaces.ISaveMatchUseCase
import com.team8.interfaces.IUpdateMatchUseCase

class UpdateMatchHandler(val updateMatchUseCase : IUpdateMatchUseCase, val saveMatchUseCase: ISaveMatchUseCase) {

    //Recibir match con categorias
    val id: Int = 0
    val round = Round()
    fun holaMundo()
    {
        updateMatchUseCase(id, round)
    }

    //UpdateMatchStatus responsabilidad del match



    //SaveMatch en memoria
}
