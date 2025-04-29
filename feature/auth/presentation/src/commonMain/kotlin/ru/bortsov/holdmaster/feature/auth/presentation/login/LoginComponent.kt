package ru.bortsov.holdmaster.feature.auth.presentation.login

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
import ru.bortsov.holdmaster.feature.auth.presentation.login.Login.LoginEvent

internal class LoginComponent(
    componentContext: ComponentContext,
    private val authRepository: AuthRepository,
    private val navigateToSignUp: () -> Unit,
    private val navigateForgotPassword: () -> Unit,
    private val navigateToMain: () -> Unit,
    private val showError: (RootError) -> Unit,
) : Login, BaseComponent<LoginState>(
    componentContext = componentContext,
    initialState = LoginState(),
    serializer = LoginState.serializer()
) {

    override val state: Value<LoginState>
        get() = viewState

    override fun obtainEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailTextChanged -> obtainEmailTextChanged(event.value)
            is LoginEvent.PasswordTextChanged -> obtainPasswordTextChanged(event.value)
            LoginEvent.OnForgotPasswordClicked -> obtainForgotPasswordClicked()
            LoginEvent.OnLoginClicked -> obtainLoginClicked()
            LoginEvent.OnSignUpClicked -> obtainSignUpClicked()
            LoginEvent.OnChangePasswordVisibilityClicked -> obtainPasswordVisibilityChange()
        }
    }

    private fun obtainEmailTextChanged(value: String) {
        updateState { copy(email = value) }
    }

    private fun obtainPasswordTextChanged(value: String) {
        updateState { copy(password = value) }
    }

    private fun obtainPasswordVisibilityChange() {
        updateState { copy(isPasswordVisible = !isPasswordVisible) }
    }

    private fun obtainLoginClicked() {
        updateState { copy(buttonState = ButtonState.Loading) }
        scope.launch(Dispatchers.IO) {
            when (val result = authRepository.login(email = state.value.email, password = state.value.password)) {
                is ResultOf.Success<Unit> -> withContext(Dispatchers.Main) {
                    updateState { copy(buttonState = ButtonState.Enabled) }
                    navigateToMain()
                }
                is ResultOf.Failure<AuthError.LoginError> -> withContext(Dispatchers.Main) {
                    updateState { copy(buttonState = ButtonState.Enabled) }
                    showError(result.error)
                }
            }
        }
    }

    private fun obtainSignUpClicked() = navigateToSignUp()

    private fun obtainForgotPasswordClicked() = navigateForgotPassword()

    class Factory(
        private val authRepository: AuthRepository,
    ) : Login.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            navigateToSignUp: () -> Unit,
            navigateForgotPassword: () -> Unit,
            navigateToMain: () -> Unit,
            showError: (RootError) -> Unit,
        ): Login {
            return LoginComponent(
                componentContext = componentContext,
                authRepository = authRepository,
                navigateToSignUp = navigateToSignUp,
                navigateForgotPassword = navigateForgotPassword,
                navigateToMain = navigateToMain,
                showError = showError,
            )
        }
    }
}