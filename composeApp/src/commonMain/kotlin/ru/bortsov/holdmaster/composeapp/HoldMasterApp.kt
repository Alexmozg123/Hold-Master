package ru.bortsov.holdmaster.composeapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HoldMasterApp() {
    MaterialTheme {
        Surface(
            Modifier
                .background(Color.White)
                .fillMaxSize(),
        ) {
            Text(
                text = "Hello, HoldMaster!",
                modifier = Modifier.fillMaxSize(),
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@[Preview Composable]
internal fun HoldMasterAppPreview() {
    HoldMasterApp()
}
