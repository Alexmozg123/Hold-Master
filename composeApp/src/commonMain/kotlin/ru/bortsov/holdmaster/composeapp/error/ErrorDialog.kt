package ru.bortsov.holdmaster.composeapp.error

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import ru.bortsov.holdmaster.core.utils.RootError

interface ErrorDialog {

    val state: Value<DialogState>
    fun onDismissClick()

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onDismiss: () -> Unit,
            error: RootError,
        ): ErrorDialog
    }
}