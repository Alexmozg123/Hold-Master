package ru.bortsov.holdmaster.core.uikit.widgets.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme

@Composable
internal fun BaseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonText: String,
    buttonState: ButtonState,
    height: Dp = 60.dp,
    shape: Shape = HoldMasterTheme.shapes.full,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    textStyle: TextStyle = HoldMasterTheme.typography.subtitle1,
    elevation: ButtonElevation = ButtonDefaults
        .elevatedButtonElevation(HoldMasterTheme.elevations.none)
) {
    Button(
        onClick = onClick,
        enabled = buttonState is ButtonState.Enabled,
        modifier = modifier.fillMaxWidth().height(height),
        shape = shape,
        colors = colors,
        elevation = elevation
    ) {
        when (buttonState) {
            is ButtonState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(30.dp),
                    color = HoldMasterTheme.colors.secondaryTextColor
                )
            }
            is ButtonState.Disabled, ButtonState.Enabled -> {
                Text(
                    text = buttonText,
                    style = textStyle,
                )
            }
        }
    }
}

@Serializable
sealed class ButtonState {
    @Serializable
    data object Enabled : ButtonState()
    @Serializable
    data object Disabled : ButtonState()
    @Serializable
    data object Loading : ButtonState()
}