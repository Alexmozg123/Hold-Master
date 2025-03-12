package ru.bortsov.holdmaster.composeapp

import org.koin.dsl.module

internal val appModule = module {
    single { "Hello koin!" }
}