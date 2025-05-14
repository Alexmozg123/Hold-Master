package ru.bortsov.holdmaster.composeapp

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.bortsov.holdmaster.composeapp.root.Root
import ru.bortsov.holdmaster.composeapp.root.RootComponent
import ru.bortsov.holdmaster.composeapp.error.ErrorComponent
import ru.bortsov.holdmaster.composeapp.error.ErrorDialog
import ru.bortsov.holdmaster.composeapp.splash.Splash
import ru.bortsov.holdmaster.composeapp.splash.SplashComponent

internal val appModule = module {
    singleOf(SplashComponent::Factory) bind Splash.Factory::class
    singleOf(ErrorComponent::Factory) bind ErrorDialog.Factory::class
    singleOf(RootComponent::Factory) bind Root.Factory::class
}