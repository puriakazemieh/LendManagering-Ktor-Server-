package com.example.model

import kotlinx.serialization.Serializable
import org.mindrot.jbcrypt.BCrypt

@Serializable
data class UserCredentials(
    val mobile: String,
    val name: String,
    val active: Boolean?=false,
    val join_date: String,
    val modification_date: String?=null,
    val family_id: String?=null,
    val password: String,
) {

    fun hashedPassword(): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    fun isValidCredentials(): Boolean {
        return name.length >= 3 && password.length >= 6
    }
}
