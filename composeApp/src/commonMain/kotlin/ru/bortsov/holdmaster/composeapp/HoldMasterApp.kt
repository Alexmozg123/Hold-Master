package ru.bortsov.holdmaster.composeapp

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.bortsov.holdmaster.composeapp.decompose.Root
import ru.bortsov.holdmaster.composeapp.decompose.splash.SplashScreen
import ru.bortsov.holdmaster.composeapp.error.ErrorAlert
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme
import ru.bortsov.holdmaster.feature.auth.presentation.navigation.AuthUi
import ru.bortsov.holdmaster.feature.photo.presentation.TakePhotoScreen

@Composable
fun HoldMasterApp(
    component: Root,
    modifier: Modifier = Modifier,
) {
    HoldMasterTheme {
        Children(component = component, modifier = modifier)
    }
}

@OptIn(ExperimentalDecomposeApi::class)
@Composable
private fun Children(
    component: Root,
    modifier: Modifier = Modifier
) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = predictiveBackAnimation(
            backHandler = component.backHandler,
            fallbackAnimation = stackAnimation(fade()),
            onBack = component::onBackClicked,
        ),
    ) {child ->
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            contentWindowInsets = WindowInsets.safeContent,
            containerColor = HoldMasterTheme.colors.primaryBackground
        ) {
            when (val current = child.instance) {

                is Root.Child.SplashChild -> SplashScreen(
                    modifier = Modifier.fillMaxSize(),
                    component = current.component
                )

                is Root.Child.AuthChild -> AuthUi(
                    modifier = modifier.fillMaxSize(),
                    component = current.component
                )

                is Root.Child.TakePhotoChild -> TakePhotoScreen(
                    modifier = modifier.fillMaxSize(),
                    component = current.component
                )

                is Root.Child.OnboardingChild -> Unit
                is Root.Child.TabsChild -> Unit
            }

            val dialogSlot by component.slot.subscribeAsState()
            when (val current = dialogSlot.child?.instance) {
                is Root.SlotChild.ErrorDialogChild -> ErrorAlert(component = current.component)
                null -> Unit
            }
        }
    }
}
