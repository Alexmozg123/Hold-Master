package ru.bortsov.holdmaster.feature.auth.presentation.forgot

import com.arkivanov.decompose.ComponentContext

internal interface ForgotPassword {

    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): ForgotPassword
    }
}