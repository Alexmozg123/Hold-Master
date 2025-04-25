package ru.bortsov.holdmaster.feature.auth.presentation.singUp

import com.arkivanov.decompose.ComponentContext

internal class SingUpComponent(
    private val componentContext: ComponentContext,
) : SingUp, ComponentContext by componentContext {

    class Factory : SingUp.Factory {
        override fun invoke(componentContext: ComponentContext): SingUp {
            return SingUpComponent(componentContext)
        }
    }
}