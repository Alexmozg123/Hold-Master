package ru.bortsov.holdmaster.feature.auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import holdmaster.feature.auth.presentation.generated.resources.Res
import holdmaster.feature.auth.presentation.generated.resources.camera_icon
import holdmaster.feature.auth.presentation.generated.resources.eyes_close
import holdmaster.feature.auth.presentation.generated.resources.eyes_open
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme
import ru.bortsov.holdmaster.core.uikit.widgets.buttons.DarkButton
import ru.bortsov.holdmaster.core.uikit.widgets.textInput.BaseTextInput
import ru.bortsov.holdmaster.feature.auth.presentation.login.Login.LoginEvent

@Composable
internal fun LoginScreen(
    modifier: Modifier = Modifier,
    component: Login,
) {
    val state by component.state.subscribeAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(Res.drawable.camera_icon),
                modifier = Modifier.size(32.dp),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Авторизация",
                style = HoldMasterTheme.typography.h4,
                color = HoldMasterTheme.colors.primaryTextColor,
            )
        }

        Spacer(modifier = Modifier.height(44.dp))

        BaseTextInput(
            value = state.email,
            onValueChange = { component.obtainEvent(LoginEvent.EmailTextChanged(it)) },
            label = "Почта",
        )

        Spacer(modifier = Modifier.height(24.dp))

        BaseTextInput(
            value = state.password,
            onValueChange = { component.obtainEvent(LoginEvent.PasswordTextChanged(it)) },
            label = "Пароль",
            trailingIcon = {
                if (state.isPasswordVisible) {
                    ImageButton(
                        onClick = { component.obtainEvent(LoginEvent.OnChangePasswordVisibilityClicked) },
                        icon = Res.drawable.eyes_open,
                    )
                } else {
                    ImageButton(
                        onClick = { component.obtainEvent(LoginEvent.OnChangePasswordVisibilityClicked) },
                        icon = Res.drawable.eyes_close,
                    )
                }
            },
            visualTransformation = if (state.isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .align(Alignment.End)
                .clickable { component.obtainEvent(LoginEvent.OnForgotPasswordClicked) },
            text = "Забыли пароль?",
            style = HoldMasterTheme.typography.body2,
            color = HoldMasterTheme.colors.secondaryTextColor,
        )

        Spacer(modifier = Modifier.height(42.dp))

        DarkButton(
            onClick = { component.obtainEvent(LoginEvent.OnLoginClicked) },
            buttonText = "Войти",
            buttonState = state.buttonState,
        )

        Spacer(modifier = Modifier.height(44.dp))

        Row(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "У вас нет аккаунта? ",
                style = HoldMasterTheme.typography.body2,
                color = HoldMasterTheme.colors.secondaryTextColor,
            )

            Text(
                modifier = Modifier
                    .clickable { component.obtainEvent(LoginEvent.OnSignUpClicked) },
                text = "Зарегистрироваться",
                style = HoldMasterTheme.typography.body2,
                color = HoldMasterTheme.colors.accentTextColor,
            )
        }
    }
}

@Composable
private fun ImageButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: DrawableResource,
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = HoldMasterTheme.colors.whiteColor,
        ),
        elevation = CardDefaults.cardElevation(HoldMasterTheme.elevations.none),
    ) {
        Image(
            painter = painterResource(icon),
            modifier = modifier.size(28.dp),
            contentDescription = null
        )
    }
}