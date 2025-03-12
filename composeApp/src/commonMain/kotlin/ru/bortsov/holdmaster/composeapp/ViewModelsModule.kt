package ru.bortsov.holdmaster.composeapp

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val viewModelsModule = module {
    viewModelOf(::MainViewModel)
}