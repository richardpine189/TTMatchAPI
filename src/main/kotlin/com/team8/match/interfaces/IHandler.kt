package com.team8.match.interfaces
import io.ktor.server.application.*

interface IHandler {
    fun routing(a: Application)
}