package ru.bortsov.holdmaster.core.base.tokenManager

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val tokenManagerModule: Module = module {
    singleOf(::TokenManagerImpl) bind TokenManager::class
}