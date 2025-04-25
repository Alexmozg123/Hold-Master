package ru.bortsov.holdmaster.feature.auth.presentation.confirm

import com.arkivanov.decompose.ComponentContext

internal class ConfirmComponent(
    private val componentContext: ComponentContext
) : Confirm, ComponentContext by componentContext {

    class Factory : Confirm.Factory {
        override fun invoke(componentContext: ComponentContext): Confirm {
            return ConfirmComponent(componentContext)
        }
    }
}