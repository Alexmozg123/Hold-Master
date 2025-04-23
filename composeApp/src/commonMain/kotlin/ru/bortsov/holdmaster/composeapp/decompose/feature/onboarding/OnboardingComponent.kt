package ru.bortsov.holdmaster.composeapp.decompose.feature.onboarding

import com.arkivanov.decompose.ComponentContext

class OnboardingComponent(
    componentContext: ComponentContext
): Onboarding, ComponentContext by componentContext {

    class Factory : Onboarding.Factory {
        override fun invoke(componentContext: ComponentContext): OnboardingComponent {
            return OnboardingComponent(componentContext = componentContext)
        }
    }
}