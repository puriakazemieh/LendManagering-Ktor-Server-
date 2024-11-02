package com.example.controller


import com.example.model.UserCredentials
import com.example.repository.UserRepositoryImp
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.component.KoinComponent

class AuthController(private val userRepository: UserRepositoryImp) : KoinComponent {

    suspend fun registerUser(call: RoutingCall) {
        val userCredentials = call.receive<UserCredentials>()

        val username = userCredentials.username.toLowerCase()

        val user = userRepository.checkIfUserExists(username)
        if (user != null) {
            call.respondText("User already exists", status = HttpStatusCode.Created)
        } else {
            call.respondText("User not exists", status = HttpStatusCode.Created)
        }
    }

    suspend fun loginUser(call: ApplicationCall) {

    }
}