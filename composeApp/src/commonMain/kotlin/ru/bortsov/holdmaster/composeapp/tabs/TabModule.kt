package ru.bortsov.holdmaster.composeapp.tabs

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val tabModule: Module = module {
    singleOf(TabsComponent::Factory) bind Tabs.Factory::class
}