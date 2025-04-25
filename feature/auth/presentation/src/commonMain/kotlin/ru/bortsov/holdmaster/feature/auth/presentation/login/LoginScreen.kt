package ru.bortsov.holdmaster.feature.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme

@Composable
internal fun LoginScreen(
    modifier: Modifier = Modifier,
    component: Login,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom
    ) {
        TitleBlock(modifier = Modifier.padding(start = 28.dp, bottom = 66.dp))

        Box(
            modifier = Modifier.fillMaxWidth()
                .height(600.dp)
                .background(
                    color = HoldMasterTheme.colors.primaryBackground,
                    shape = RoundedCornerShape(
                        topStart = 40.dp,
                        topEnd = 40.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp,
                    )
                ),
        ) {
            Text(
                modifier = Modifier.padding(28.dp),
                text = "Тута, здеся, будет форма авторизации",
                style = HoldMasterTheme.typography.bodyLarge,
                color = HoldMasterTheme.colors.primaryTextColor,
            )
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
            style = HoldMasterTheme.typography.displayMedium,
            color = HoldMasterTheme.colors.whiteColor,
            fontWeight = FontWeight.ExtraBold,
        )

        Text(
            text = "Добро пожаловать в HoldMaster",
            style = HoldMasterTheme.typography.bodyLarge,
            color = HoldMasterTheme.colors.whiteColor,
        )
    }
}