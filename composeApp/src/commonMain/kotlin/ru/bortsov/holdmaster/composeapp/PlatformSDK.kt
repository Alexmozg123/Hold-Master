package ru.bortsov.holdmaster.composeapp

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import ru.bortsov.holdmaster.composeapp.decompose.Root
import ru.bortsov.holdmaster.core.base.di.Inject
import ru.bortsov.holdmaster.core.base.di.Inject.di
import ru.bortsov.holdmaster.core.base.platform.PlatformConfig
import ru.bortsov.holdmaster.feature.photo.data.photoDataModule
import ru.bortsov.holdmaster.feature.photo.presentation.photoPresentationModule

object PlatformSDK {
    fun init(config: PlatformConfig) {

        val umbrellaModule = module { single<PlatformConfig> { config } }

        Napier.base(DebugAntilog())

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

    /**
     * ТОЛЬКО ДЛЯ IOS
     *
     * Важно: убираем lazy, inline и reified
     * из-за особенностей компиляции Kotlin в Objective-C
     */
    fun getRootFactory(): Root.Factory { return di.get<Root.Factory>() }
}