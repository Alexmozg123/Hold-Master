package ru.bortsov.holdmaster.feature.auth.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
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
    val focusManager = LocalFocusManager.current

    Children(
        stack = component.stack,
        modifier = modifier,
        animation = predictiveBackAnimation(
            backHandler = component.backHandler,
            fallbackAnimation = stackAnimation(slide()),
            onBack = component::onBackClicked,
        ),
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = HoldMasterTheme.colors.secondaryBackground
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures { focusManager.clearFocus() }
                    },
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {
                if (it.instance is Auth.Child.LoginChild) {
                    TitleBlock(modifier = Modifier.padding(start = 28.dp, bottom = 66.dp))
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = HoldMasterTheme.colors.primaryBackground,
                            shape = RoundedCornerShape(
                                topStart = 40.dp,
                                topEnd = 40.dp,
                                bottomEnd = 0.dp,
                                bottomStart = 0.dp
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(28.dp)
                    ) {
                        when (val child = it.instance) {
                            is Auth.Child.LoginChild -> {
                                LoginScreen(component = child.component)
                            }

                            is Auth.Child.SingUpChild -> {
                                SingUpScreen(component = child.component)
                            }

                            is Auth.Child.ForgotPasswordChild -> {
                                ForgotScreen(component = child.component)
                            }

                            is Auth.Child.ConfirmChild -> {
                                ConfirmScreen(component = child.component)
                            }
                        }
                    }
                }
            }
        }
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