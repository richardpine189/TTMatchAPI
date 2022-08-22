package com.team8.domain

import kotlinx.serialization.Serializable
import kotlin.math.round


@Serializable
class Match(val challenger: String, val opponent: String) {


    fun SetAnswers(answers: Array<String>) {
        if (matchTurn == MatchTurn.Challenger)
            rounds[currentRound].challengerAnswers = answers
        else
            rounds[currentRound].opponentAnswers = answers

        updateRoundStatus()
    }

    private fun updateRoundStatus() {
        if (rounds[currentRound].roundStatus == RoundStatus.Unfinished) {
            rounds[currentRound].roundStatus = RoundStatus.Finished
            currentRound++
        }
        if(rounds[currentRound].roundStatus == RoundStatus.NotStarted){
            rounds[currentRound].roundStatus = RoundStatus.Unfinished
            SwitchPlayerTurn()
        }
    }

    private fun SwitchPlayerTurn()
    {
        if(matchTurn == MatchTurn.Opponent)
            matchTurn = MatchTurn.Challenger
        else
            matchTurn = MatchTurn.Opponent
    }
    // FALTA ID
    var matchTurn = MatchTurn.Challenger
    var rounds = arrayOf<Round>(Round(), Round(), Round())
    var currentRound = 0

}

enum class MatchTurn {Challenger, Opponent}