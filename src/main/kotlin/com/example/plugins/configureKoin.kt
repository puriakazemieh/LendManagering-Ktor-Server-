package com.example.plugins

import io.ktor.server.application.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }
}

// todo sample : https://insert-koin.io/docs/quickstart/ktor/

val appModule = module {
//    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
//    singleOf(::UserService)
}