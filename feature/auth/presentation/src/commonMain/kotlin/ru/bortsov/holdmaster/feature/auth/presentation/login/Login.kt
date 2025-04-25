package ru.bortsov.holdmaster.feature.auth.presentation.login

import com.arkivanov.decompose.ComponentContext

internal interface Login {

    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): Login
    }
}