package ru.bortsov.holdmaster.feature.auth.presentation.forgot

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import ru.bortsov.holdmaster.core.utils.RootError

internal interface ForgotPassword {

    val state: Value<ForgotPasswordState>

    fun obtainEvent(event: ForgotPasswordEvent)

    sealed class ForgotPasswordEvent {
        data class EmailTextChanged(val value: String) : ForgotPasswordEvent()
        data object OnSendEmailClicked : ForgotPasswordEvent()
        data object OnBackClicked : ForgotPasswordEvent()
    }

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            navigateToLogin: () -> Unit,
            navigateToConfirm: () -> Unit,
            showError: (RootError) -> Unit,
        ): ForgotPassword
    }
}