package ru.bortsov.holdmaster.core.uikit

import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val LocalTypography = staticCompositionLocalOf { typography() }

internal fun typography(): Typography {
    return Typography(
        displayLarge = DefaultTextStyle.copy(
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold
        ),
        displayMedium = DefaultTextStyle.copy(
            fontSize = 40.sp,
        ),
        displaySmall = DefaultTextStyle.copy(
            fontSize = 36.sp,
        ),
        headlineLarge = DefaultTextStyle.copy(
            fontSize = 32.sp,
        ),
        headlineMedium = DefaultTextStyle.copy(
            fontSize = 28.sp,
        ),
        headlineSmall = DefaultTextStyle.copy(
            fontSize = 24.sp,
        ),
        titleLarge = DefaultTextStyle.copy(
            fontSize = 20.sp,
        ),
        titleMedium = DefaultTextStyle.copy(
            fontSize = 18.sp,
        ),
        titleSmall = DefaultTextStyle.copy(
            fontSize = 16.sp,
        ),
        bodyLarge = DefaultTextStyle.copy(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        ),
        bodyMedium = DefaultTextStyle.copy(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        ),
        bodySmall = DefaultTextStyle.copy(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
        ),
        labelLarge = DefaultTextStyle.copy(
            fontSize = 16.sp,
        ),
        labelMedium = DefaultTextStyle.copy(
            fontSize = 14.sp,
        ),
        labelSmall = DefaultTextStyle.copy(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
        ),
    )
}

private val DefaultTextStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Medium,
)