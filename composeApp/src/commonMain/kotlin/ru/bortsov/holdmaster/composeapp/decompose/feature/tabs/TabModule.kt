package ru.bortsov.holdmaster.composeapp.decompose.feature.tabs

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.game.Game
import ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.game.GameComponent
import ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.profile.Profile
import ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.profile.ProfileComponent

val tabModule: Module = module {
    singleOf(GameComponent::Factory) bind Game.Factory::class
    singleOf(ProfileComponent::Factory) bind Profile.Factory::class
    singleOf(TabsComponent::Factory) bind Tabs.Factory::class
}