package com.example.repository

import com.example.entities.UserEntity
import com.example.model.User
import org.koin.core.component.KoinComponent
import org.ktorm.database.Database
import org.ktorm.dsl.*

class UserRepositoryImp(private val db: Database) :  KoinComponent {

    fun checkIfUserExists(username: String) : User? {
        return db.from(UserEntity)
            .select()
            .where { UserEntity.name eq username }
            .map {
                val id = it[UserEntity.id]!!
                val username = it[UserEntity.name]!!
                val password =  it[UserEntity.password]!!
                User(id, username, password)
            }.firstOrNull()
    }

    fun createUser(username: String, password: String) {
        db.insert(UserEntity) {
            set(it.name, username)
            set(it.password, password)
        }
    }

}