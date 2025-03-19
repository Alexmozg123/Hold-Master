package ru.bortsov.holdmaster.composeapp

import org.koin.dsl.koinApplication
import org.koin.dsl.module
import ru.bortsov.holdmaster.core.base.platform.PlatformConfig
import ru.bortsov.holdmaster.feature.photo.data.photoDataModule
import ru.bortsov.holdmaster.feature.photo.presentation.photoPresentationModule

object PlatformSDK {
    fun init(config: PlatformConfig) {

        val umbrellaModule = module { single<PlatformConfig> { config } }

        Inject.createDependencies(
            koinApplication {
                modules(
                    appModule,
                    umbrellaModule,
                    photoDataModule,
                    photoPresentationModule,
                )
            }.koin
        )
    }
}