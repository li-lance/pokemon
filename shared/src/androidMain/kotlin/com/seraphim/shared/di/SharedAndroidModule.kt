package com.seraphim.shared.di

import com.seraphim.shared.repository.PokemonRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.slf4j.LoggerFactory

private val log: org.slf4j.Logger = LoggerFactory.getLogger("HttpClient")
val sharedAndroidModule = module {
    single {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true // 忽略未知字段
                    isLenient = true // 宽松模式
                })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        log.debug(message)
                    }
                }
                level = LogLevel.ALL // 打印所有日志
            }
        }
    }
    single { PokemonRepository(get(), get()) }
    single { Factory(get()).createRoomDatabase() }

}