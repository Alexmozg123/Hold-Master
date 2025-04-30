package ru.bortsov.holdmaster.feature.auth.presentation.forgot

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.bortsov.holdmaster.core.uikit.widgets.buttons.ButtonState
import ru.bortsov.holdmaster.core.utils.BaseComponent
import ru.bortsov.holdmaster.core.utils.ResultOf
import ru.bortsov.holdmaster.core.utils.RootError
import ru.bortsov.holdmaster.feature.auth.api.AuthError
import ru.bortsov.holdmaster.feature.auth.api.AuthRepository
import ru.bortsov.holdmaster.feature.auth.presentation.forgot.ForgotPassword.ForgotPasswordEvent

internal class ForgotPasswordComponent(
    private val componentContext: ComponentContext,
    private val authRepository: AuthRepository,
    private val navigateToLogin: () -> Unit,
    private val navigateToConfirm: () -> Unit,
    private val showError: (RootError) -> Unit,
): ForgotPassword, BaseComponent<ForgotPasswordState>(
    componentContext = componentContext,
    initialState = ForgotPasswordState(),
    serializer = ForgotPasswordState.serializer()
) {

    override val state: Value<ForgotPasswordState> get() = viewState

    override fun obtainEvent(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.EmailTextChanged -> obtainEmailTextChanged(event.value)
            ForgotPasswordEvent.OnSendEmailClicked -> obtainSendEmailClicked()
            ForgotPasswordEvent.OnBackClicked -> navigateToLogin()
        }
    }

    private fun obtainSendEmailClicked() {
        updateState { copy(buttonState = ButtonState.Loading) }
        scope.launch(Dispatchers.IO) {
            when (val result = authRepository.forgotPassword(email = state.value.email)) {
                is ResultOf.Success<Unit> -> withContext(Dispatchers.Main) {
                    updateState { copy(buttonState = ButtonState.Enabled) }
                    navigateToConfirm()
                }
                is ResultOf.Failure<AuthError.ForgotPasswordError> -> withContext(Dispatchers.Main) {
                    updateState { copy(buttonState = ButtonState.Enabled) }
                    showError(result.error)
                }
            }
        }
    }

    private fun obtainEmailTextChanged(value: String) {
        updateState { copy(email = value) }
    }

    class Factory(
        private val authRepository: AuthRepository,
    ) : ForgotPassword.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            navigateToLogin: () -> Unit,
            navigateToConfirm: () -> Unit,
            showError: (RootError) -> Unit
        ): ForgotPassword {
            return ForgotPasswordComponent(
                componentContext = componentContext,
                authRepository = authRepository,
                navigateToLogin = navigateToLogin,
                navigateToConfirm = navigateToConfirm,
                showError = showError,
            )
        }
    }
}