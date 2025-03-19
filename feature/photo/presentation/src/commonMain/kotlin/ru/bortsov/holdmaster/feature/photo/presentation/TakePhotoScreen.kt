package ru.bortsov.holdmaster.feature.photo.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.bortsov.holdmaster.core.uikit.HoldMasterTheme
import ru.bortsov.holdmaster.feature.photo.presentation.component.Photo

@Composable
fun TakePhotoScreen(
    modifier: Modifier = Modifier,
    component: Photo,
) {
    val state by component.state.subscribeAsState()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = HoldMasterTheme.colors.primaryBackground
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            state.bitmap?.let { notNullBitmap ->
                Image(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    bitmap = notNullBitmap,
                    contentDescription = "Some bitmap"
                )
            } ?: Text(
                text = "We don`t know where is a pic...",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = HoldMasterTheme.colors.primaryTextColor,
                style = HoldMasterTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(HoldMasterTheme.spaces.medium))

            Text(
                text = "Click on button below!",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = HoldMasterTheme.colors.secondaryTextColor,
                style = HoldMasterTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(HoldMasterTheme.spaces.medium))

            Card(
                onClick = { component.onTakePhotoClick() },
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
                        text = "Take a photo",
                        color = HoldMasterTheme.colors.accentTextColor,
                        style = HoldMasterTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}