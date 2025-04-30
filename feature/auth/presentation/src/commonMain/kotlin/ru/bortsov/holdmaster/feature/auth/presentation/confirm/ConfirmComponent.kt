package ru.bortsov.holdmaster.feature.auth.presentation.confirm

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import ru.bortsov.holdmaster.core.utils.BaseComponent
import ru.bortsov.holdmaster.feature.auth.api.AuthRepository
import ru.bortsov.holdmaster.feature.auth.presentation.confirm.Confirm.ConfirmEvent

internal class ConfirmComponent(
    componentContext: ComponentContext,
    private val authRepository: AuthRepository,
) : Confirm, BaseComponent<ConfirmState>(
    componentContext = componentContext,
    initialState = ConfirmState(),
    serializer = ConfirmState.serializer()
) {

    override val state: Value<ConfirmState> get() = viewState

    override fun obtainEvent(event: ConfirmEvent) {
        when (event) {
            is ConfirmEvent.CodeTextChanged -> TODO()
            ConfirmEvent.OnBackClicked -> TODO()
            ConfirmEvent.OnConfirmClicked -> TODO()
            ConfirmEvent.OnRetrySendClicked -> TODO()
        }
    }

    class Factory(
        private val authRepository: AuthRepository,
    ) : Confirm.Factory {
        override fun invoke(componentContext: ComponentContext): Confirm {
            return ConfirmComponent(
                componentContext = componentContext,
                authRepository = authRepository,
            )
        }
    }
}