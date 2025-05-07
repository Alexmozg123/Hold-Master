package ru.bortsov.holdmaster.core.base.authStatusManager

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val authStatusManagerModule: Module = module {
    singleOf(::AuthStatusManagerImpl) bind AuthStatusManager::class
}