package com.example.entities

import org.ktorm.schema.*

object UserEntity : Table<Nothing>("users") {
    val id = int("id").primaryKey()
    val mobile = varchar("mobile")
    val name = varchar("name")
    val active = boolean("active")
    val join_date = datetime("join_date")
    val modification_date = datetime("modification_date")
    val family_id = int("family_id")
    val password = varchar("password")
}