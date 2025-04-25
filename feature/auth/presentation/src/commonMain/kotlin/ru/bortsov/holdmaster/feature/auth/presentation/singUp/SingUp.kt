package ru.bortsov.holdmaster.feature.auth.presentation.singUp

import com.arkivanov.decompose.ComponentContext

internal interface SingUp {

    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): SingUp
    }
}