package ru.bortsov.holdmaster.core.uikit.widgets.textInput

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme

@Composable
fun BaseTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    labelAlign: TextAlign = TextAlign.Start,
    isEnabled: Boolean = true,
    shape: Shape = HoldMasterTheme.shapes.extraSmall
        .copy(bottomStart = ZeroCornerSize, bottomEnd = ZeroCornerSize),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    textStyle: TextStyle = HoldMasterTheme.typography.bodyLarge,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        enabled = isEnabled,
        label = {
            label?.let {
                Text(
                    text = it,
                    textAlign = labelAlign,
                    fontFamily = FontFamily.Default,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        shape = shape,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(),
        textStyle = textStyle,
        visualTransformation = visualTransformation,
        singleLine = singleLine
    )
//    backgroundColor = HoldMasterTheme.colors.accentTextColor.copy(alpha = 0.1f),
//    cursorColor = HoldMasterTheme.colors.accentTextColor,
//    textColor = HoldMasterTheme.colors.primaryTextColor,
//    unfocusedIndicatorColor = HoldMasterTheme.colors.secondaryTextColor,
//    focusedIndicatorColor = HoldMasterTheme.colors.accentTextColor,
//    focusedLabelColor =  HoldMasterTheme.colors.accentTextColor,
//    unfocusedLabelColor = HoldMasterTheme.colors.secondaryTextColor
}