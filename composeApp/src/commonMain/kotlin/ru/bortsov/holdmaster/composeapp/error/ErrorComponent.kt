package ru.bortsov.holdmaster.composeapp.error

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import ru.bortsov.holdmaster.core.utils.BaseComponent
import ru.bortsov.holdmaster.core.utils.RootError

class ErrorComponent(
    componentContext: ComponentContext,
    error: RootError,
    private val onDismiss: () -> Unit,
): ErrorDialog, BaseComponent<DialogState>(
    componentContext = componentContext,
    initialState = DialogState(error = error),
    serializer = DialogState.serializer()
) {
    override val state: Value<DialogState> get() = viewState

    override fun onDismissClick() = onDismiss()

    class Factory : ErrorDialog.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onDismiss: () -> Unit,
            error: RootError
        ): ErrorDialog {
            return ErrorComponent(
                componentContext = componentContext,
                onDismiss = onDismiss,
                error = error,
            )
        }
    }
}