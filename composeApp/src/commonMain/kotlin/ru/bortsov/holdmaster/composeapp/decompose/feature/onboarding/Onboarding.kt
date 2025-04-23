package ru.bortsov.holdmaster.composeapp.decompose.feature.onboarding

import com.arkivanov.decompose.ComponentContext

interface Onboarding {
    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): Onboarding
    }
}