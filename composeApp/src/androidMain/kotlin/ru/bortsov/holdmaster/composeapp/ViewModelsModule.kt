package ru.bortsov.holdmaster.composeapp

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal actual val viewModelsModule = module {
    viewModelOf(::MainViewModel)
}