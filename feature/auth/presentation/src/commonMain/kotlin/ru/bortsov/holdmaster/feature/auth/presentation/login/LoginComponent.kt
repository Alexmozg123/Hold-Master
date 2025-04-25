package ru.bortsov.holdmaster.feature.auth.presentation.login

import com.arkivanov.decompose.ComponentContext

internal class LoginComponent(
    private val componentContext: ComponentContext
) : Login, ComponentContext by componentContext {

    class Factory : Login.Factory {
        override fun invoke(componentContext: ComponentContext): Login {
            return LoginComponent(componentContext)
        }
    }
}