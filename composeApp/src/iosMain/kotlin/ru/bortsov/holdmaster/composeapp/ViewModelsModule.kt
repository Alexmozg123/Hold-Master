package ru.bortsov.holdmaster.composeapp

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val viewModelsModule = module {
    singleOf(::MainViewModel)
}