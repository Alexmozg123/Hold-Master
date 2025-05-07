package ru.bortsov.holdmaster.composeapp.error

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.DialogProperties
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme
import ru.bortsov.holdmaster.core.uikit.widgets.buttons.DarkButton
import ru.bortsov.holdmaster.core.utils.ApiError
import ru.bortsov.holdmaster.core.utils.RootError
import ru.bortsov.holdmaster.feature.auth.api.AuthError

@Composable
internal fun ErrorAlert(
    component: ErrorDialog,
    modifier: Modifier = Modifier,
) {
    val state by component.state.subscribeAsState()

    AlertDialog(
        onDismissRequest = component::onDismissClick,
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Ошибка!",
                textAlign = TextAlign.Center,
                color = HoldMasterTheme.colors.primaryTextColor,
                fontWeight = FontWeight.Bold,
                style = HoldMasterTheme.typography.h5,
            )
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = getErrorText(state.error),
                textAlign = TextAlign.Center,
                color = HoldMasterTheme.colors.secondaryTextColor,
                style = HoldMasterTheme.typography.body2
            )
        },
        confirmButton = {
            DarkButton(
                onClick = { component.onDismissClick() },
                buttonText = "ОК"
            )
        },
        containerColor = HoldMasterTheme.colors.whiteColor,
        modifier = modifier,
        shape = HoldMasterTheme.shapes.extraLarge,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        )
    )
}

internal fun getErrorText(error: RootError): String {
    return when (error) {
        ApiError.IOExceptions.CONNECTION_ERROR -> "Плохое соединение, повторите попытку позже"
        ApiError.NetworkErrors.UNAUTHORIZED -> "Пользователь не авторизован (401)"
        ApiError.NetworkErrors.SERVER_ERROR -> "Сервер недоступен (500 - 599)"
        ApiError.NetworkErrors.UNKNOWN_ERROR -> "Неизветная ошибка сервера"
        ApiError.NetworkErrors.FORBIDDEN -> "Ошибка пользователя (403)"
        AuthError.LoginError.LoginError -> "LoginError"
        AuthError.SignUpError.SignUpError -> "SignUpError"
        AuthError.SendConfirmCodeError.SendConfirmCodeError -> "SendConfirmCodeError"
        AuthError.ForgotPasswordError.ForgotPasswordError -> "ForgotPasswordError"
        else -> "Неизветная ошибка"
    }
}