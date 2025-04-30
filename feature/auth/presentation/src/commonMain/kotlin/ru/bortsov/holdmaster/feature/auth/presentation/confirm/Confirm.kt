package ru.bortsov.holdmaster.feature.auth.presentation.confirm

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

internal interface Confirm {

    val state: Value<ConfirmState>

    fun obtainEvent(event: ConfirmEvent)

    sealed class ConfirmEvent {
        data class CodeTextChanged(val value: String) : ConfirmEvent()
        data object OnConfirmClicked : ConfirmEvent()
        data object OnRetrySendClicked : ConfirmEvent()
        data object OnBackClicked : ConfirmEvent()
    }

    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): Confirm
    }
}