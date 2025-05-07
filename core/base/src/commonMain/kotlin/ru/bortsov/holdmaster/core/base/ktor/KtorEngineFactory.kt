package ru.bortsov.holdmaster.core.base.ktor

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

internal expect class KtorEngineFactory() {
    fun getEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}