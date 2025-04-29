package ru.bortsov.holdmaster.feature.auth.presentation.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import ru.bortsov.holdmaster.core.utils.RootError

internal interface Login {

    val state: Value<LoginState>

    fun obtainEvent(event: LoginEvent)

    sealed class LoginEvent {
        data class EmailTextChanged(val value: String) : LoginEvent()
        data class PasswordTextChanged(val value: String) : LoginEvent()
        data object OnChangePasswordVisibilityClicked : LoginEvent()
        data object OnLoginClicked : LoginEvent()
        data object OnSignUpClicked : LoginEvent()
        data object OnForgotPasswordClicked : LoginEvent()
    }

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            navigateToSignUp: () -> Unit,
            navigateForgotPassword: () -> Unit,
            navigateToMain: () -> Unit,
            showError: (RootError) -> Unit,
        ): Login
    }
}