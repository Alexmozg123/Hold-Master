package ru.bortsov.holdmaster.feature.auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
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
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            },
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom
    ) {
        TitleBlock(modifier = Modifier.padding(start = 28.dp, bottom = 66.dp))

        Box(
            modifier = Modifier.fillMaxWidth()
                .background(
                    color = HoldMasterTheme.colors.primaryBackground,
                    shape = RoundedCornerShape(
                        topStart = 40.dp,
                        topEnd = 40.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp,
                    )
                ),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(28.dp),
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
                        .align(alignment = Alignment.End)
                        .clickable { component.obtainEvent(LoginEvent.OnForgotPasswordClicked) },
                    text = "Забыли пароль?",
                    style = HoldMasterTheme.typography.body2,
                    color = HoldMasterTheme.colors.secondaryTextColor,
                )

                Spacer(modifier = Modifier.height(42.dp))

                DarkButton(
                    onClick = { component.obtainEvent(LoginEvent.OnLoginClicked) },
                    buttonText = "Войти",
                )

                Spacer(modifier = Modifier.height(44.dp))

                Text(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .clickable { component.obtainEvent(LoginEvent.OnSignUpClicked) },
                    text = "У вас нет аккаунта? Зарегистрироваться",
                    style = HoldMasterTheme.typography.body2,
                    color = HoldMasterTheme.colors.secondaryTextColor,
                )
            }
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

@Composable
private fun TitleBlock(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(HoldMasterTheme.spaces.extraSmall),
    ) {
        Text(
            text = "Привет!",
            style = HoldMasterTheme.typography.h1,
            color = HoldMasterTheme.colors.whiteColor,
            fontWeight = FontWeight.ExtraBold,
        )

        Text(
            text = "Добро пожаловать в HoldMaster",
            style = HoldMasterTheme.typography.body1,
            color = HoldMasterTheme.colors.whiteColor,
        )
    }
}