package ru.bortsov.holdmaster.feature.auth.presentation.confirm

import kotlinx.serialization.Serializable
import ru.bortsov.holdmaster.core.uikit.widgets.buttons.ButtonState

@Serializable
data class ConfirmState(
    val confirmCode: String = "",
    val buttonState: ButtonState = ButtonState.Enabled,
)