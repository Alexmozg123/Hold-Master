package ru.bortsov.holdmaster.composeapp

import org.koin.core.context.startKoin

actual class KoinStarter {
    actual fun init() {
        startKoin {
            modules(appModule, viewModelsModule)
        }
    }
}