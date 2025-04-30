package ru.bortsov.holdmaster.feature.auth.presentation.confirm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import holdmaster.feature.auth.presentation.generated.resources.Res
import holdmaster.feature.auth.presentation.generated.resources.camera_icon
import org.jetbrains.compose.resources.painterResource
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme
import ru.bortsov.holdmaster.core.uikit.widgets.buttons.DarkButton
import ru.bortsov.holdmaster.feature.auth.presentation.confirm.Confirm.ConfirmEvent

@Composable
internal fun ConfirmScreen(
    modifier: Modifier = Modifier,
    component: Confirm,
) {
    val state by component.state.subscribeAsState()

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 36.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom
    ) {
        Card(
            onClick = { component.obtainEvent(ConfirmEvent.OnBackClicked) },
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            elevation = CardDefaults.cardElevation(HoldMasterTheme.elevations.none)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(4.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = HoldMasterTheme.colors.primaryTextColor,
                    modifier = Modifier.size(12.dp),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Назад к авторизации",
                    style = HoldMasterTheme.typography.body2,
                    color = HoldMasterTheme.colors.primaryTextColor,
                )
            }
        }

        Row {
            Image(
                painter = painterResource(Res.drawable.camera_icon),
                modifier = Modifier.size(32.dp),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Проверьте почту",
                style = HoldMasterTheme.typography.h4,
                color = HoldMasterTheme.colors.primaryTextColor,
            )
        }

        Spacer(modifier = Modifier.height(44.dp))

        Text(
            text = "Введите код для востановления пароля",
            style = HoldMasterTheme.typography.body1,
            color = HoldMasterTheme.colors.primaryTextColor,
        )

        Spacer(modifier = Modifier.height(16.dp))

        ConfirmTextInput(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            confirmCode = state.confirmCode,
            onValueChange = { component.obtainEvent(ConfirmEvent.CodeTextChanged(it)) },
        )

        Spacer(modifier = Modifier.height(44.dp))

        DarkButton(
            onClick = { component.obtainEvent(ConfirmEvent.OnConfirmClicked) },
            buttonText = "Отправить код",
            buttonState = state.buttonState,
        )
    }
}

@Composable
private fun ConfirmTextInput(
    modifier: Modifier = Modifier,
    confirmCode: String,
    onValueChange: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = modifier.clickable { focusRequester.requestFocus() }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(4) { index ->
                val char = confirmCode.getOrNull(index)
                SymbolWindow(value = char?.takeIf { it.isLetterOrDigit() } ?: ' ')
            }
        }

        BasicTextField(
            value = confirmCode,
            onValueChange = {
                if (it.length <= 4 && it.all { c -> c.isLetterOrDigit() }) {
                    onValueChange(it)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            modifier = Modifier
                .focusRequester(focusRequester)
                .size(0.dp)
                .alpha(0f)
        )
    }
}

@Composable
private fun SymbolWindow(
    modifier: Modifier = Modifier,
    value: Char,
) {
    Box(
        modifier = modifier
            .size(width = 76.dp, height = 104.dp)
            .background(
                color = HoldMasterTheme.colors.whiteColor,
                shape = HoldMasterTheme.shapes.extraLarge
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = value.toString(),
            style = HoldMasterTheme.typography.h1,
            color = HoldMasterTheme.colors.primaryTextColor,
        )
    }
}