package ru.bortsov.holdmaster.feature.auth.presentation.forgot

import com.arkivanov.decompose.ComponentContext

internal class ForgotPasswordComponent(
    private val componentContext: ComponentContext
): ForgotPassword, ComponentContext by componentContext {

    class Factory : ForgotPassword.Factory {
        override fun invoke(componentContext: ComponentContext): ForgotPassword {
            return ForgotPasswordComponent(componentContext)
        }
    }
}