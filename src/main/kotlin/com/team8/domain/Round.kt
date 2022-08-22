package com.team8.domain

import kotlinx.serialization.Serializable
import kotlin.reflect.jvm.internal.impl.protobuf.LazyStringArrayList

@Serializable
class Round(){

    var roundStatus: RoundStatus = RoundStatus.NotStarted
    var categoryNames : Array<String> = Array(5) {""}
    var challengerAnswers : Array<String> = Array(5) {""}
    var opponentAnswers : Array<String> = Array(5) {""}
    fun setCategoriesToRound(){

    }
}

enum class RoundStatus {NotStarted, Unfinished, Finished}