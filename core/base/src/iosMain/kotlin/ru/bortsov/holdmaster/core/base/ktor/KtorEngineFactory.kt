package ru.bortsov.holdmaster.core.base.ktor

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

internal actual class KtorEngineFactory {
    actual fun getEngine(): HttpClientEngineFactory<HttpClientEngineConfig> = Darwin
}