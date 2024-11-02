package com.example.plugins

import com.example.controller.AuthController
import com.example.db.DatabaseConnection
import com.example.repository.UserRepositoryImp
import io.ktor.server.application.*
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
    single { AuthController(get()) }
    single { UserRepositoryImp(get()) }
    single { DatabaseConnection.database }
}