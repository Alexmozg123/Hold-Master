package ru.bortsov.holdmaster.core.base

import org.koin.core.module.Module
import org.koin.dsl.module
import ru.bortsov.holdmaster.core.base.ktor.ktorModule
import ru.bortsov.holdmaster.core.base.settings.settingsModule

public val coreModule: Module = module {
    includes(
        ktorModule,
        settingsModule,
    )
}