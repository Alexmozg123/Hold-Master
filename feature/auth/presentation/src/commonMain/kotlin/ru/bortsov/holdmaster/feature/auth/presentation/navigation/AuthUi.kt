package ru.bortsov.holdmaster.feature.auth.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme
import ru.bortsov.holdmaster.feature.auth.presentation.confirm.ConfirmScreen
import ru.bortsov.holdmaster.feature.auth.presentation.forgot.ForgotScreen
import ru.bortsov.holdmaster.feature.auth.presentation.login.LoginScreen
import ru.bortsov.holdmaster.feature.auth.presentation.singUp.SingUpScreen

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun AuthUi(
    modifier: Modifier = Modifier,
    component: Auth,
) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = predictiveBackAnimation(
            backHandler = component.backHandler,
            fallbackAnimation = stackAnimation(fade() + scale()),
            onBack = component::onBackClicked,
        ),
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = HoldMasterTheme.colors.primaryBackground
        ) {
            when (val child = it.instance) {
                is Auth.Child.ConfirmChild -> ConfirmScreen(
                    modifier = Modifier.fillMaxSize(),
                    component = child.component
                )

                is Auth.Child.ForgotPasswordChild -> ForgotScreen(
                    modifier = Modifier.fillMaxSize(),
                    component = child.component
                )

                is Auth.Child.LoginChild -> LoginScreen(
                    modifier = Modifier.fillMaxSize(),
                    component = child.component
                )

                is Auth.Child.SingUpChild -> SingUpScreen(
                    modifier = Modifier.fillMaxSize(),
                    component = child.component
                )
            }
        }
    }
}