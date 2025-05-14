package ru.bortsov.holdmaster.feature.profile.presentation

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val profilePresentationModule: Module = module {
    singleOf(ProfileComponent::Factory) bind Profile.Factory::class
}