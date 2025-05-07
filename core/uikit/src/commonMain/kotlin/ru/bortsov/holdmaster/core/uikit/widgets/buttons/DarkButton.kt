package ru.bortsov.holdmaster.core.uikit.widgets.buttons

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme

@Composable
fun DarkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonText: String,
    buttonState: ButtonState = ButtonState.Enabled,
) {
    BaseButton(
        onClick = onClick,
        buttonText = buttonText,
        modifier = modifier,
        buttonState = buttonState,
        colors = ButtonDefaults.buttonColors(
            contentColor = HoldMasterTheme.colors.whiteColor,
            containerColor = HoldMasterTheme.colors.secondaryBackground,
            disabledContentColor = HoldMasterTheme.colors.secondaryTextColor,
            disabledContainerColor = HoldMasterTheme.colors.secondaryTextColor
        )
    )
}