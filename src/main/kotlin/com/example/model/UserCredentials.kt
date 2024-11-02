package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class UserCredentials(
    val username: String,
    val password: String
) {

    fun isValidCredentials(): Boolean {
        return username.length >= 3 && password.length >= 6
    }
}
