package ru.bortsov.holdmaster.composeapp

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.bortsov.holdmaster.composeapp.decompose.Root
import ru.bortsov.holdmaster.composeapp.decompose.RootComponent
import ru.bortsov.holdmaster.composeapp.decompose.feature.onboarding.Onboarding
import ru.bortsov.holdmaster.composeapp.decompose.feature.onboarding.OnboardingComponent
import ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.Tabs
import ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.TabsComponent
import ru.bortsov.holdmaster.composeapp.decompose.splash.Splash
import ru.bortsov.holdmaster.composeapp.decompose.splash.SplashComponent
import ru.bortsov.holdmaster.composeapp.error.ErrorComponent
import ru.bortsov.holdmaster.composeapp.error.ErrorDialog

internal val appModule = module {
    singleOf(SplashComponent::Factory) bind Splash.Factory::class
    singleOf(TabsComponent::Factory) bind Tabs.Factory::class
    singleOf(OnboardingComponent::Factory) bind Onboarding.Factory::class
    singleOf(ErrorComponent::Factory) bind ErrorDialog.Factory::class
    singleOf(RootComponent::Factory) bind Root.Factory::class
}