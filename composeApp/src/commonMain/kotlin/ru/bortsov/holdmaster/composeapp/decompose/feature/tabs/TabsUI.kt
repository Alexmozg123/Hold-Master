package ru.bortsov.holdmaster.composeapp.decompose.feature.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import holdmaster.composeapp.generated.resources.Res
import holdmaster.composeapp.generated.resources.open_eyes_icon
import holdmaster.composeapp.generated.resources.rocket_icon
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme

@Composable
fun TabsUi(
    modifier: Modifier = Modifier,
    component: Tabs,
) {
    Box(modifier = modifier) {
        Children(
            component = component,
            modifier = Modifier.fillMaxSize().align(Alignment.Center)
        )
        BottomBar(
            component = component,
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun Children(component: Tabs, modifier: Modifier = Modifier) {
    val state by component.stack.subscribeAsState()

    LaunchedEffect(state.active) {
        Napier.d { state.active.toString() }
    }
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(fade() + scale()),
    ) {
        when (val child = it.instance) {
            is Tabs.Child.GameChild -> Box(Modifier.fillMaxWidth().background(Color.Blue))
            is Tabs.Child.ProfileChild -> Box(Modifier.fillMaxWidth().background(Color.Red))
        }
    }
}

@Composable
private fun BottomBar(
    component: Tabs,
    modifier: Modifier = Modifier
) {
    val stack by component.stack.subscribeAsState()
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 20.dp)
            .navigationBarsPadding()
            .background(
                color = HoldMasterTheme.colors.accentTextColor,
                shape = HoldMasterTheme.shapes.extraLarge,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (stack.active.instance is Tabs.Child.GameChild) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .weight(1f)
                            .background(Color.White),
                    )
                } else {
                    Spacer(Modifier.weight(1f))
                }

                Box(
                    modifier = Modifier.padding(vertical = 12.dp).weight(1f)
                ) {
                    Box(modifier = Modifier.height(46.dp).background(Color.White))
                }

//                if (stack.active.instance is Tabs.Child.ProfileChild) {
//                    Box(
//                        modifier = Modifier
//                            .padding(vertical = 12.dp)
//                            .weight(1f)
//                            .background(Color.White),
//                    )
//                } else {
//                    Spacer(Modifier.weight(1f))
//                }
            }

            Row(
                modifier = Modifier.zIndex(2f),
            ) {
                BottomIcon(
                    modifier = Modifier.weight(1f),
                    onClick = component::onGameClicked,
                    image = Res.drawable.rocket_icon
                )

                BottomIcon(
                    modifier = Modifier.weight(1f),
                    onClick = component::onProfileTabClicked,
                    image = Res.drawable.open_eyes_icon
                )
            }
        }
    }
}

@Composable
private fun BottomIcon(
    modifier: Modifier = Modifier,
    image: DrawableResource,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        shape = HoldMasterTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors().copy(containerColor = Color.Transparent),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(image),
                modifier = Modifier.size(36.dp),
                contentDescription = "Bottom icon",
            )
        }
    }
}