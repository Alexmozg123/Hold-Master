package ru.bortsov.holdmaster.feature.auth.presentation.singUp

import kotlinx.serialization.Serializable
import ru.bortsov.holdmaster.core.uikit.widgets.buttons.ButtonState
import ru.bortsov.holdmaster.feature.auth.api.model.SingUpForm

@Serializable
data class SingUpState(
    val singUpForm: SingUpForm = emptyForm(),
    val buttonState: ButtonState = ButtonState.Enabled,
) {
    companion object {
        fun emptyForm(): SingUpForm = SingUpForm(
            name = "",
            middleName = "",
            lastName = "",
            email = "",
            password = "",
        )
    }
}
