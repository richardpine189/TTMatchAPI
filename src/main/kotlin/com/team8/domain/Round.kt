package com.team8.domain

import kotlinx.serialization.Serializable

@Serializable
class Round() {

    var roundStatus: RoundStatus = RoundStatus.NotStarted
    var categoryNames : Array<String> = Array(5) {""}
    var challengerAnswers : Array<String> = Array(5) {""}
    var opponentAnswers : Array<String> = Array(5) {""}
    var challengerResults : Array<Boolean> = Array(5){ false }
    var opponentResults : Array<Boolean> = Array(5){ false }
    var letter : Char? = null
    var timeLeft : Int? = null
    var winner : WinnerStatus = WinnerStatus.Unassigned

    fun updateRoundStatus() {
        if (roundStatus == RoundStatus.Unfinished)
        {
            roundStatus = RoundStatus.Finished

            setRoundWinner()
        }

        if(roundStatus == RoundStatus.NotStarted)
        {
            roundStatus = RoundStatus.Unfinished
        }
    }

    private fun setRoundWinner()
    {
        if(challengerResults.count{ it } > opponentResults.count{ it })
        {
            winner = WinnerStatus.Challenger
        }
        else if(challengerResults.count{ it } < opponentResults.count{ it })
        {
            winner = WinnerStatus.Opponent
        }
        else if(challengerResults.count{ it } == opponentResults.count{ it })
        {
            winner = WinnerStatus.Draw
        }
    }
}

enum class RoundStatus {NotStarted, Unfinished, Finished}