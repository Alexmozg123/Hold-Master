package ru.bortsov.holdmaster.feature.auth.presentation.forgot

import kotlinx.serialization.Serializable
import ru.bortsov.holdmaster.core.uikit.widgets.buttons.ButtonState

@Serializable
data class ForgotPasswordState(
    val email: String = "",
    val buttonState: ButtonState = ButtonState.Enabled,
)
