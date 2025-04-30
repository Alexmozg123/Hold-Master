package ru.bortsov.holdmaster.feature.auth.presentation.singUp

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
import ru.bortsov.holdmaster.feature.auth.presentation.singUp.SingUp.SingUpEvent

internal class SingUpComponent(
    componentContext: ComponentContext,
    private val authRepository: AuthRepository,
    private val navigateToLogin: () -> Unit,
    private val navigateToConfirm: () -> Unit,
    private val showError: (RootError) -> Unit,
) : SingUp, BaseComponent<SingUpState>(
    componentContext = componentContext,
    initialState = SingUpState(),
    serializer = SingUpState.serializer()
) {

    override val state: Value<SingUpState> get() = viewState

    override fun obtainEvent(event: SingUpEvent) {
        when (event) {
            is SingUpEvent.NameTextChanged -> obtainNameTextChanged(event.value)
            is SingUpEvent.MiddleNameTextChanged -> obtainMiddleNameTextChanged(event.value)
            is SingUpEvent.LastNameTextChanged -> obtainLastNameTextChanged(event.value)
            is SingUpEvent.PasswordTextChanged -> obtainPasswordTextChanged(event.value)
            is SingUpEvent.EmailTextChanged -> obtainEmailTextChanged(event.value)
            SingUpEvent.OnSignUpClicked -> obtainSignUpClicked()
            SingUpEvent.OnBackClicked -> navigateToLogin()
        }
    }

    private fun obtainSignUpClicked() {
        updateState { copy(buttonState = ButtonState.Loading) }
        scope.launch(Dispatchers.IO) {
            when (val result = authRepository.singUp(singUpForm = state.value.singUpForm)) {
                is ResultOf.Success<Unit> -> withContext(Dispatchers.Main) {
                    updateState {
                        copy(
                            buttonState = ButtonState.Enabled,
                            singUpForm = SingUpState.emptyForm()
                        )
                    }
                    navigateToConfirm()
                }
                is ResultOf.Failure<AuthError.SignUpError> -> withContext(Dispatchers.Main) {
                    updateState { copy(buttonState = ButtonState.Enabled) }
                    showError(result.error)
                }
            }
        }
    }

    private fun obtainNameTextChanged(value: String) {
        updateState { copy(singUpForm = singUpForm.copy(name = value)) }
    }

    private fun obtainMiddleNameTextChanged(value: String) {
        updateState { copy(singUpForm = singUpForm.copy(middleName = value)) }
    }

    private fun obtainLastNameTextChanged(value: String) {
        updateState { copy(singUpForm = singUpForm.copy(lastName = value)) }
    }

    private fun obtainPasswordTextChanged(value: String) {
        updateState { copy(singUpForm = singUpForm.copy(password = value)) }
    }

    private fun obtainEmailTextChanged(value: String) {
        updateState { copy(singUpForm = singUpForm.copy(email = value)) }
    }

    class Factory(
        private val authRepository: AuthRepository,
    ) : SingUp.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            navigateToLogin: () -> Unit,
            navigateToConfirm: () -> Unit,
            showError: (RootError) -> Unit
        ): SingUp {
            return SingUpComponent(
                componentContext = componentContext,
                authRepository = authRepository,
                navigateToLogin = navigateToLogin,
                navigateToConfirm = navigateToConfirm,
                showError = showError,
            )
        }
    }
}