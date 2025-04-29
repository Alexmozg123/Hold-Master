package ru.bortsov.holdmaster.core.uikit.widgets.textInput

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    shape: Shape = HoldMasterTheme.shapes.full,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    textStyle: TextStyle = HoldMasterTheme.typography.body1,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        enabled = isEnabled,
        placeholder = {
            label?.let {
                Text(
                    text = it,
                    textAlign = labelAlign,
                    fontFamily = FontFamily.Default,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        trailingIcon = trailingIcon,
        shape = shape,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = HoldMasterTheme.colors.whiteColor,
            unfocusedContainerColor = HoldMasterTheme.colors.whiteColor,
            cursorColor = HoldMasterTheme.colors.primaryTextColor,
            focusedTextColor = HoldMasterTheme.colors.primaryTextColor,
            unfocusedTextColor = HoldMasterTheme.colors.secondaryTextColor,
            unfocusedPlaceholderColor = HoldMasterTheme.colors.thirdTextColor,
            focusedPlaceholderColor = HoldMasterTheme.colors.secondaryTextColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        textStyle = textStyle,
        visualTransformation = visualTransformation,
        singleLine = singleLine
    )
}