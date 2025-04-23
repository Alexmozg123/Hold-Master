package ru.bortsov.holdmaster.composeapp.decompose.splash

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme

@Composable
internal fun StartScreen(
    modifier: Modifier = Modifier,
    component: Splash,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = HoldMasterTheme.colors.primaryBackground
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Hello, HoldMaster!",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = HoldMasterTheme.colors.primaryTextColor,
                style = HoldMasterTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(HoldMasterTheme.spaces.medium))

            Card(
                onClick = { component.onGoToPhotoFeatureClick() },
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
                        text = "Go to PhotoFeature",
                        color = HoldMasterTheme.colors.accentTextColor,
                        style = HoldMasterTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}