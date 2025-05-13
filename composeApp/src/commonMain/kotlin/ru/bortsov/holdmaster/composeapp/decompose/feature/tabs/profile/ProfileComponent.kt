package ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.profile

import com.arkivanov.decompose.ComponentContext

class ProfileComponent(
    componentContext: ComponentContext
): Profile, ComponentContext by componentContext {

    class Factory : Profile.Factory {
        override fun invoke(componentContext: ComponentContext): Profile {
            return ProfileComponent(componentContext = componentContext)
        }
    }
}