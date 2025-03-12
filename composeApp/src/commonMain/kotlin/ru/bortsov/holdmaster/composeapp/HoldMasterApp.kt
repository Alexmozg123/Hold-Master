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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme

@Composable
fun HoldMasterApp() {
    HoldMasterTheme {
        KoinContext {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "startScreen"
            ) {
                composable("startScreen") {
                    val viewModel = koinViewModel<MainViewModel>()
                    val state by viewModel.timer.collectAsState()
                    StartScreen(state)
                }
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

@[Preview Composable]
internal fun HoldMasterAppPreview() {
    HoldMasterApp()
}
