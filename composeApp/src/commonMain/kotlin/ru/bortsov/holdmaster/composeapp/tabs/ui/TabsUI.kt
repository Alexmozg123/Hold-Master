package ru.bortsov.holdmaster.composeapp.tabs.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import holdmaster.composeapp.generated.resources.Res
import holdmaster.composeapp.generated.resources.open_eyes_icon
import holdmaster.composeapp.generated.resources.rocket_icon
import ru.bortsov.holdmaster.composeapp.tabs.Tabs

@Composable
fun TabsUi(
    modifier: Modifier = Modifier,
    component: Tabs,
) {
    val state by component.stack.subscribeAsState()

    Box(modifier = modifier) {
        Children(
            modifier = Modifier.fillMaxSize().align(Alignment.Center),
            component = component,
        )
        BottomBar(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            tabs = listOf(
                BottomTab(
                    icon = Res.drawable.rocket_icon,
                    onClick = component::onGameClicked,
                    isSelected = state.active.instance is Tabs.Child.GameChild
                ),
                BottomTab(
                    icon = Res.drawable.open_eyes_icon,
                    onClick = component::onProfileTabClicked,
                    isSelected = state.active.instance is Tabs.Child.ProfileChild
                ),
            )
        )
    }
}

@Composable
private fun Children(
    modifier: Modifier = Modifier,
    component: Tabs,
) {
    val state by component.stack.subscribeAsState()

    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is Tabs.Child.GameChild -> Box(Modifier.fillMaxSize().background(Color.Blue))
            is Tabs.Child.ProfileChild -> Box(Modifier.fillMaxSize().background(Color.Red))
        }
    }
}