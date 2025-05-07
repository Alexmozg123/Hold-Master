package ru.bortsov.holdmaster.feature.auth.presentation.singUp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import ru.bortsov.holdmaster.core.utils.RootError

internal interface SingUp {

    val state: Value<SingUpState>

    fun obtainEvent(event: SingUpEvent)

    sealed class SingUpEvent {
        data class NameTextChanged(val value: String) : SingUpEvent()
        data class MiddleNameTextChanged(val value: String) : SingUpEvent()
        data class LastNameTextChanged(val value: String) : SingUpEvent()
        data class EmailTextChanged(val value: String) : SingUpEvent()
        data class PasswordTextChanged(val value: String) : SingUpEvent()
        data object OnSignUpClicked : SingUpEvent()
        data object OnBackClicked : SingUpEvent()
    }

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            navigateToLogin: () -> Unit,
            navigateToConfirm: () -> Unit,
            showError: (RootError) -> Unit,
        ): SingUp
    }
}