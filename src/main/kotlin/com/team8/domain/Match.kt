package com.team8.domain

import kotlinx.serialization.Serializable


@Serializable
class Match(val challenger: String, val opponent: String, var id : Int = -1)
{
    // Estos tres métodos públicos (setCategories, setResults y setAnswers) deberían ser privados y ser llamados desde un método único que reciba un RoundDTO
    // aparte de llamar él a updateRoundStatus


    fun setCategories(categories : Array<String>) {
        rounds[currentRound].categoryNames = categories
    }
    fun setAnswers(answers: Array<String>) {
        if (matchTurn == MatchTurn.Challenger)
            rounds[currentRound].challengerAnswers = answers
        else
            rounds[currentRound].opponentAnswers = answers
    }
    fun setResults(results : Array<Boolean>) {
        if (matchTurn == MatchTurn.Challenger)
            rounds[currentRound].challengerResults = results
        else
            rounds[currentRound].opponentResults = results

        rounds[currentRound].updateRoundStatus()

        if(rounds[currentRound].roundStatus == RoundStatus.Finished)
        {
            currentRound++
        }
        else
        {
            switchPlayerTurn()
        }

        if(rounds[rounds.size-1].roundStatus == RoundStatus.Finished)
        {
            setMatchWinner()
        }
    }

    fun setLetter(letter : Char)
    {
        if(rounds[currentRound].letter != null)
            rounds[currentRound].letter = letter
    }

    fun setTimeLeft(timeLeft : Int)
    {
        if(rounds[currentRound].timeLeft != null)
            rounds[currentRound].timeLeft = timeLeft
    }


    private fun switchPlayerTurn()
    {
        if(matchTurn == MatchTurn.Opponent)
            matchTurn = MatchTurn.Challenger
        else
            matchTurn = MatchTurn.Opponent
    }

    private fun setMatchWinner()
    {
        if(rounds.count{ it.winner == WinnerStatus.Challenger } > rounds.count{ it.winner == WinnerStatus.Opponent })
        {
            winner = WinnerStatus.Challenger
        }
        else if(rounds.count{ it.winner == WinnerStatus.Challenger } < rounds.count{ it.winner == WinnerStatus.Opponent })
        {
            winner = WinnerStatus.Opponent
        }
        else if(rounds.count{ it.winner == WinnerStatus.Challenger } == rounds.count{ it.winner == WinnerStatus.Opponent })
        {
            winner = WinnerStatus.Draw
        }

    }


    var matchTurn = MatchTurn.Challenger
    var rounds = arrayOf<Round>(Round(), Round(), Round())
    var currentRound = 0

    var winner : WinnerStatus = WinnerStatus.Unassigned

}

enum class MatchTurn {Challenger, Opponent}

enum class WinnerStatus { Unassigned, Challenger, Opponent, Draw}