package ru.bortsov.holdmaster.feature.auth.presentation.login

import kotlinx.serialization.Serializable
import ru.bortsov.holdmaster.core.uikit.widgets.buttons.ButtonState

@Serializable
data class LoginState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLeaveFromScreen: Boolean = true,
    val buttonState: ButtonState = ButtonState.Enabled,
)
