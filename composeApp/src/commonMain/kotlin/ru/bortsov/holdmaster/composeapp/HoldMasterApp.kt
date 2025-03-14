package ru.bortsov.holdmaster.composeapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.bortsov.holdmaster.composeapp.decompose.Root
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme

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
            fallbackAnimation = stackAnimation(fade() + scale()),
            onBack = component::onBackClicked,
        ),
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = HoldMasterTheme.colors.primaryBackground
        ) {
            when (val child = it.instance) {
                Root.Child.SplashChild -> StartScreen(0)
                is Root.Child.AuthChild -> Unit
                is Root.Child.OnboardingChild -> Unit
                is Root.Child.TabsChild -> Unit
            }

            val dialogSlot by component.slot.subscribeAsState()
            when (val child = dialogSlot.child?.instance) {
                is Root.SlotChild.ErrorDialogChild -> Unit
                null -> Unit
            }
        }
    }
}

@Composable
private fun StartScreen(
    currentTimer: Int,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = HoldMasterTheme.colors.primaryBackground
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Hello, HoldMaster!\nTimer: $currentTimer",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = HoldMasterTheme.colors.primaryTextColor,
                style = HoldMasterTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(HoldMasterTheme.spaces.medium))


            Card(
                onClick = {},
                modifier = Modifier.height(70.dp).width(150.dp),
                shape = HoldMasterTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = HoldMasterTheme.colors.darkAction),
                elevation = CardDefaults.cardElevation(defaultElevation = HoldMasterTheme.elevations.small)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "This is uikit test",
                        color = HoldMasterTheme.colors.accentTextColor,
                        style = HoldMasterTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}
