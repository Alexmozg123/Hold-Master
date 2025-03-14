package ru.bortsov.holdmaster.composeapp.error

import com.arkivanov.decompose.ComponentContext

class ErrorComponent(
    componentContext: ComponentContext
): ErrorDialog, ComponentContext by componentContext {

    class Factory : ErrorDialog.Factory {
        override fun invoke(componentContext: ComponentContext): ErrorDialog {
            return ErrorComponent(componentContext = componentContext)
        }
    }
}