package ru.bortsov.holdmaster.composeapp.error

import com.arkivanov.decompose.ComponentContext

interface ErrorDialog {
    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): ErrorDialog
    }
}