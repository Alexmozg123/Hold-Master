package ru.bortsov.holdmaster.composeapp.decompose.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme

@Composable
internal fun SplashScreen(
    modifier: Modifier = Modifier,
    component: Splash,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = HoldMasterTheme.colors.secondaryBackground
    ) {
        // TODO: Здесь необходимо будет добавить анимацию и логотип
    }
}