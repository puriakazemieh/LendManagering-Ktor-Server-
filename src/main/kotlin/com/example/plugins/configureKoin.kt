package com.example.plugins

import com.example.controller.AuthController
import com.example.db.DatabaseConnection
import com.example.repository.UserRepositoryImp
import com.example.utils.TokenManager
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }
}


val appModule = module {
    single { AuthController(get(),get()) }
    single { UserRepositoryImp(get()) }
    single { TokenManager(HoconApplicationConfig(ConfigFactory.load())) }
    single { DatabaseConnection.database }
}