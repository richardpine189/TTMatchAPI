package com.team8.domain

import kotlinx.serialization.Serializable
import kotlin.math.round


@Serializable
class Match(val challenger: String, val opponent: String, var id : Int = -1) {

    // Estos tres métodos públicos (setCategories, setResults y setAnswers) deberían ser privados y ser llamados desde un método único que reciba un RoundDTO
    // aparte de llamar él a updateRoundStatus

    fun setCategories(categories : Array<String>) {
        rounds[currentRound].categoryNames = categories
    }

    fun setResults(results : Array<Boolean>) {
        if (matchTurn == MatchTurn.Challenger)
            rounds[currentRound].challengerResults = results
        else
            rounds[currentRound].opponentResults = results
    }

    fun setAnswers(answers: Array<String>) {
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
            switchPlayerTurn()
        }
    }

    private fun switchPlayerTurn()
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