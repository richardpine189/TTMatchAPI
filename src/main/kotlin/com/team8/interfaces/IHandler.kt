package com.team8.interfaces
import io.ktor.server.application.*

interface IHandler {
    fun routing(a: Application)
}