package com.example.route

import com.example.controller.AuthController
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.authenticationRoutes() {

    val authController by inject<AuthController>()

    routing {
        post("/login") {
            authController.loginUser(call)
        }
        post("/register") {
            authController.registerUser(call)
        }
    }

}