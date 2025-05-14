package ru.bortsov.holdmaster.composeapp.tabs.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme

@Composable
internal fun BottomBar(
    modifier: Modifier = Modifier,
    tabs: List<BottomTab>,
) {
    val selectedIndex = remember(tabs) { tabs.indexOfFirst { it.isSelected }.coerceAtLeast(0) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 20.dp)
            .navigationBarsPadding(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults
            .cardColors(containerColor = HoldMasterTheme.colors.secondaryBackground)
    ) {
        BoxWithConstraints(
            modifier = modifier.fillMaxWidth().padding(6.dp),
            contentAlignment = Alignment.Center,
        ) {
            val totalWidth = with(LocalDensity.current) { constraints.maxWidth.toDp() }
            val tabWidth = remember { totalWidth / tabs.size }
            val targetOffset = remember(selectedIndex) { tabWidth * selectedIndex }

            val indicatorOffset by animateDpAsState(
                targetValue = targetOffset,
                label = "indicator_offset"
            )

            Box {
                Box(
                    modifier = Modifier
                        .offset(x = indicatorOffset)
                        .width(tabWidth)
                        .height(46.dp)
                        .background(
                            color = HoldMasterTheme.colors.whiteColor,
                            shape = RoundedCornerShape(14.dp),
                        )
                        .align(Alignment.CenterStart)
                        .zIndex(1f)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .zIndex(2f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    tabs.forEach { tab ->
                        BottomIcon(
                            modifier = Modifier.weight(1f),
                            image = tab.icon,
                            onClick = tab.onClick
                        )
                    }
                }
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
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Color.Transparent),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(5.dp),
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