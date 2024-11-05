package com.example.controller


import com.example.model.NoteResponse
import com.example.model.UserCredentials
import com.example.repository.UserRepositoryImp
import com.example.utils.TokenManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.mindrot.jbcrypt.BCrypt

class AuthController(private val userRepository: UserRepositoryImp, private val tokenManager: TokenManager) : KoinComponent {

    suspend fun registerUser(call: ApplicationCall) {
        val userCredentials = call.receive<UserCredentials>()

        if (!userCredentials.isValidCredentials()) {
            call.respond(
                HttpStatusCode.BadRequest,
                NoteResponse(success = false, data = "Username should be greater than or equal to 3 and password should be greater than or equal to 8")
            )
            return
        }

        val username = userCredentials.name.toLowerCase()
        val password = userCredentials.hashedPassword()

        // Check if username already exists
        val user = userRepository.checkIfUserExists(username)

        if(user != null) {
            call.respond(
                HttpStatusCode.BadRequest,
                NoteResponse(success = false, data = "User already exists, please try a different username")
            )
            return
        }

        userRepository.createUser(username, password)

        call.respond(
            HttpStatusCode.Created,
            NoteResponse(success = true, data = "User has been successfully created")
        )
    }

    suspend fun loginUser(call: ApplicationCall) {
        val userCredentials = call.receive<UserCredentials>()

        if (!userCredentials.isValidCredentials()) {
            call.respond(HttpStatusCode.BadRequest,
                NoteResponse(success = false,
                    data = "Username should be greater than or equal to 3 and password should be greater than or equal to 8")
            )
            return
        }

        val username = userCredentials.name.toLowerCase()
        val password = userCredentials.password

        // Check if user exists
        val user = userRepository.checkIfUserExists(username)

        if(user == null) {
            call.respond(HttpStatusCode.BadRequest,
                NoteResponse(success = false, data = "Invalid username or password."))
            return
        }

        val doesPasswordMatch = BCrypt.checkpw(password, user.password)
        if(!doesPasswordMatch) {
            call.respond(HttpStatusCode.BadRequest,
                NoteResponse(success = false, data = "Invalid username or password."))
            return
        }

        val token = tokenManager.generateJWTToken(user)
        call.respond(
            HttpStatusCode.OK,
            NoteResponse(success = true, data = token)
        )
    }
}