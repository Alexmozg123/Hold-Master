package ru.bortsov.holdmaster.composeapp

import org.koin.dsl.koinApplication
import org.koin.dsl.module

object PlatformSDK {
    fun init(config: PlatformConfig) {

        val umbrellaModule = module { single<PlatformConfig> { config } }

        Inject.createDependencies(
            koinApplication {
                modules(appModule, umbrellaModule)
            }.koin
        )
    }
}