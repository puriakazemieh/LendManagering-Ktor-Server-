package com.example.db

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import org.ktorm.database.Database

object DatabaseConnection {

    private var config = HoconApplicationConfig(ConfigFactory.load())

    val database = Database.connect(
        url = config.property("DB_URL").getString(),
        driver =  config.property("DB_DRIVER").getString(),
        user = config.property("DB_USER").getString(),
        password = config.property("DB_PASSWORD").getString(),
    )

}