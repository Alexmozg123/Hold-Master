package ru.bortsov.holdmaster.core.base

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.bortsov.holdmaster.core.base.authStatusManager.authStatusManagerModule
import ru.bortsov.holdmaster.core.base.ktor.ktorModule
import ru.bortsov.holdmaster.core.base.settings.settingsModule
import ru.bortsov.holdmaster.core.base.tokenManager.tokenManagerModule

public val coreModule: Module = module {
    includes(
        ktorModule,
        settingsModule,
        tokenManagerModule,
        authStatusManagerModule,
    )
}