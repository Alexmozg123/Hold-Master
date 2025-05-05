package ru.bortsov.holdmaster.feature.auth.presentation.confirm

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import holdmaster.feature.auth.presentation.generated.resources.Res
import holdmaster.feature.auth.presentation.generated.resources.camera_icon
import org.jetbrains.compose.resources.painterResource
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme
import ru.bortsov.holdmaster.feature.auth.presentation.confirm.Confirm.ConfirmEvent

@Composable
internal fun ConfirmScreen(
    modifier: Modifier = Modifier,
    component: Confirm,
) {
    val state by component.state.subscribeAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

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
            isLoading = state.isLoading,
            onValueChange = { component.obtainEvent(ConfirmEvent.CodeTextChanged(it)) },
            onReady = { component.obtainEvent(ConfirmEvent.OnConfirmClicked) }
        )

        Spacer(modifier = Modifier.height(44.dp))
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ConfirmTextInput(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    confirmCodeLength: Int = 4,
    confirmCode: String,
    onValueChange: (String) -> Unit,
    onReady: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(confirmCode) {
        if (confirmCode.length == confirmCodeLength) {
            keyboardController?.hide()
            onReady()
        }
    }

    BasicTextField(
        value = confirmCode,
        onValueChange = {
            if (it.length <= confirmCodeLength && it.all(Char::isLetterOrDigit)) {
                onValueChange(it)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(confirmCodeLength) { index ->
                val char = confirmCode.getOrNull(index)
                SymbolWindow(
                    value = char?.takeIf { it.isLetterOrDigit() } ?: ' ',
                    isLoading = isLoading
                )
            }
        }
    }
}

@Composable
private fun SymbolWindow(
    modifier: Modifier = Modifier,
    value: Char,
    isLoading: Boolean,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "LoadingPulse")

    val animatedAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "AlphaPulse"
    )

    val backgroundColor = if (value.isLetterOrDigit()) {
        HoldMasterTheme.colors.accentTextColor.copy(alpha = 0.3f)
    } else {
        HoldMasterTheme.colors.whiteColor
    }

    Box(
        modifier = modifier
            .size(width = 76.dp, height = 104.dp)
            .graphicsLayer {
                alpha = if (isLoading) animatedAlpha else 1f
            }
            .background(
                color = backgroundColor,
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