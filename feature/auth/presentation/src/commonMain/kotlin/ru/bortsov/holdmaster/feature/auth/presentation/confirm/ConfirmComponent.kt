package ru.bortsov.holdmaster.feature.auth.presentation.confirm

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.bortsov.holdmaster.core.utils.BaseComponent
import ru.bortsov.holdmaster.core.utils.ResultOf
import ru.bortsov.holdmaster.core.utils.RootError
import ru.bortsov.holdmaster.feature.auth.api.AuthError
import ru.bortsov.holdmaster.feature.auth.api.AuthRepository
import ru.bortsov.holdmaster.feature.auth.presentation.confirm.Confirm.ConfirmEvent

internal class ConfirmComponent(
    componentContext: ComponentContext,
    private val authRepository: AuthRepository,
    private val navigateToLogin: () -> Unit,
    private val showError: (RootError) -> Unit,
) : Confirm, BaseComponent<ConfirmState>(
    componentContext = componentContext,
    initialState = ConfirmState(),
    serializer = ConfirmState.serializer()
) {

    override val state: Value<ConfirmState> get() = viewState

    override fun obtainEvent(event: ConfirmEvent) {
        when (event) {
            is ConfirmEvent.CodeTextChanged -> obtainCodeTextChanged(event.value)
            ConfirmEvent.OnConfirmClicked -> obtainConfirmClicked()
            ConfirmEvent.OnRetrySendClicked -> TODO()
            ConfirmEvent.OnBackClicked -> navigateToLogin()
        }
    }

    private fun obtainConfirmClicked() {
        updateState { copy(isLoading = true) }
        scope.launch(Dispatchers.IO) {
            delay(3000)
            when (val result = authRepository.sendConfirmCode(code = state.value.confirmCode)) {
                is ResultOf.Success<Unit> -> withContext(Dispatchers.Main) {
                    updateState { copy(isLoading = false) }
                    navigateToLogin()
                }
                is ResultOf.Failure<AuthError.SendConfirmCodeError> -> withContext(Dispatchers.Main) {
                    updateState { copy(isLoading = false, confirmCode = "") }
                    showError(result.error)
                }
            }
        }
    }

    private fun obtainRetrySendClicked() {
        updateState { copy(isLoading = true) }
    }

    private fun obtainCodeTextChanged(value: String) {
        updateState { copy(confirmCode = value) }
    }

    class Factory(
        private val authRepository: AuthRepository,
    ) : Confirm.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            navigateToLogin: () -> Unit,
            showError: (RootError) -> Unit
        ): Confirm {
            return ConfirmComponent(
                componentContext = componentContext,
                authRepository = authRepository,
                navigateToLogin = navigateToLogin,
                showError = showError,
            )
        }
    }
}