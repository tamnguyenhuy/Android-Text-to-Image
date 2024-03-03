package com.vproject.stablediffusion.di

import com.vproject.stablediffusion.util.TestUtil
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

actual fun platformModule() = module {
    single { TestUtil() }

    single {
        HttpClient {
            install(ContentNegotiation) {
                json( Json { ignoreUnknownKeys = true })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 120000
                connectTimeoutMillis = 120000
                socketTimeoutMillis = 120000
            }
        }
    }
}