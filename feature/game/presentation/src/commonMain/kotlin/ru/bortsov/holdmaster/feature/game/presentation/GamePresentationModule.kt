package ru.bortsov.holdmaster.feature.game.presentation

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val gamePresentationModule: Module = module {
    singleOf(GameComponent::Factory) bind Game.Factory::class
}