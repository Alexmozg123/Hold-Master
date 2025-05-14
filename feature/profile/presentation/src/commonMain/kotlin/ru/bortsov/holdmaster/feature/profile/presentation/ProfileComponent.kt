package ru.bortsov.holdmaster.feature.profile.presentation

import com.arkivanov.decompose.ComponentContext

internal class ProfileComponent(
    componentContext: ComponentContext
): Profile, ComponentContext by componentContext {

    class Factory : Profile.Factory {
        override fun invoke(componentContext: ComponentContext): Profile {
            return ProfileComponent(componentContext = componentContext)
        }
    }
}