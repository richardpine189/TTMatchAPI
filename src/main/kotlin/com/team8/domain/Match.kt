package com.team8.domain

import kotlinx.serialization.Serializable


@Serializable
class Match(val challenger: String, val opponent: String) {
    fun SetAnswers(answers: Array<String>) {

        if(matchTurn == MatchTurn.Challenger)
            rounds[0].challengerAnswers = answers
        else
            rounds[0].opponentAnswers = answers

        SwitchPlayerTurn()
    }

    private fun SwitchPlayerTurn()
    {
        if(matchTurn == MatchTurn.Opponent)
            matchTurn = MatchTurn.Challenger

        else
            matchTurn = MatchTurn.Opponent
    }

    var matchTurn = MatchTurn.Challenger
    var rounds = arrayOf<Round>(Round(), Round(), Round())

}



enum class MatchTurn {Challenger, Opponent}