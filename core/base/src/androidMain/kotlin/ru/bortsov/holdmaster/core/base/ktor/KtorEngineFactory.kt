package ru.bortsov.holdmaster.core.base.ktor

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

internal actual class KtorEngineFactory {
    actual fun getEngine(): HttpClientEngineFactory<HttpClientEngineConfig> = OkHttp
}