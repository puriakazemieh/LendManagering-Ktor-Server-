package com.example.plugins

import com.example.route.authenticationRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        authenticationRoutes()
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
