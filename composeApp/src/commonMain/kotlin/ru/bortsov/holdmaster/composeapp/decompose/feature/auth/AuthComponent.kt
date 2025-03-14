package ru.bortsov.holdmaster.composeapp.decompose.feature.auth

import com.arkivanov.decompose.ComponentContext

class AuthComponent(
    componentContext: ComponentContext
): Auth, ComponentContext by componentContext {

    class Factory : Auth.Factory {
        override fun invoke(componentContext: ComponentContext): AuthComponent {
            return AuthComponent(componentContext = componentContext)
        }
    }
}