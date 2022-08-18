package com.team8.domain

import kotlinx.serialization.Serializable


@Serializable
class Match(val challenger: String, val opponent: String) {

}