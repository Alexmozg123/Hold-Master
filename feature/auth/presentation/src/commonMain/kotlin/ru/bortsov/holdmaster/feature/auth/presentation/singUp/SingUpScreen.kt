package ru.bortsov.holdmaster.feature.auth.presentation.singUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import holdmaster.feature.auth.presentation.generated.resources.Res
import holdmaster.feature.auth.presentation.generated.resources.camera_icon
import org.jetbrains.compose.resources.painterResource
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme
import ru.bortsov.holdmaster.core.uikit.widgets.buttons.DarkButton
import ru.bortsov.holdmaster.core.uikit.widgets.textInput.BaseTextInput
import ru.bortsov.holdmaster.feature.auth.presentation.singUp.SingUp.SingUpEvent

@Composable
internal fun SingUpScreen(
    modifier: Modifier = Modifier,
    component: SingUp,
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
            onClick = { component.obtainEvent(SingUpEvent.OnBackClicked) },
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
                text = "Регистрация",
                style = HoldMasterTheme.typography.h4,
                color = HoldMasterTheme.colors.primaryTextColor,
            )
        }

        Spacer(modifier = Modifier.height(44.dp))

        BaseTextInput(
            value = state.singUpForm.middleName,
            onValueChange = { component.obtainEvent(SingUpEvent.MiddleNameTextChanged(it)) },
            label = "Фамилия",
        )

        Spacer(modifier = Modifier.height(16.dp))

        BaseTextInput(
            value = state.singUpForm.name,
            onValueChange = { component.obtainEvent(SingUpEvent.NameTextChanged(it)) },
            label = "Имя",
        )

        Spacer(modifier = Modifier.height(16.dp))

        BaseTextInput(
            value = state.singUpForm.lastName,
            onValueChange = { component.obtainEvent(SingUpEvent.LastNameTextChanged(it)) },
            label = "Отчество (не обязательно)",
        )

        Spacer(modifier = Modifier.height(16.dp))

        BaseTextInput(
            value = state.singUpForm.email,
            onValueChange = { component.obtainEvent(SingUpEvent.EmailTextChanged(it)) },
            label = "Почта",
        )

        Spacer(modifier = Modifier.height(16.dp))

        BaseTextInput(
            value = state.singUpForm.password,
            onValueChange = { component.obtainEvent(SingUpEvent.PasswordTextChanged(it)) },
            label = "Пароль",
        )

        Spacer(modifier = Modifier.height(44.dp))

        DarkButton(
            onClick = { component.obtainEvent(SingUpEvent.OnSignUpClicked) },
            buttonText = "Зарегистрироваться",
            buttonState = state.buttonState,
        )
    }
}