package ru.bortsov.holdmaster.composeapp

import org.koin.dsl.module
import ru.bortsov.holdmaster.composeapp.decompose.Root
import ru.bortsov.holdmaster.composeapp.decompose.RootComponent
import ru.bortsov.holdmaster.composeapp.decompose.feature.auth.Auth
import ru.bortsov.holdmaster.composeapp.decompose.feature.auth.AuthComponent
import ru.bortsov.holdmaster.composeapp.decompose.feature.onboarding.Onboarding
import ru.bortsov.holdmaster.composeapp.decompose.feature.onboarding.OnboardingComponent
import ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.Tabs
import ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.TabsComponent
import ru.bortsov.holdmaster.composeapp.error.ErrorComponent
import ru.bortsov.holdmaster.composeapp.error.ErrorDialog

internal val appModule = module {
    single<Tabs.Factory> { TabsComponent.Factory() }
    single<Auth.Factory> { AuthComponent.Factory() }
    single<Onboarding.Factory> { OnboardingComponent.Factory() }
    single<ErrorDialog.Factory> { ErrorComponent.Factory() }
    single<Root.Factory> { RootComponent.Factory(get(), get(), get(), get()) }

    single { "Hello koin!" }
}