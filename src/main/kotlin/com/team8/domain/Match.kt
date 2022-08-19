package com.team8.domain

import kotlinx.serialization.Serializable


@Serializable
class Match(val challenger: String, val opponent: String) {
    fun SetAnswers() {
        matchTurn = MatchTurn.Opponent
    }

    var matchTurn = MatchTurn.Challenger
    var rounds = arrayOf<Round>(Round(), Round(), Round())
}

@Serializable
class Round(){
    var roundStatus: RoundStatus = RoundStatus.NotStarted
}
enum class RoundStatus {NotStarted, Unfinished, Finished}
enum class MatchTurn {Challenger, Opponent}