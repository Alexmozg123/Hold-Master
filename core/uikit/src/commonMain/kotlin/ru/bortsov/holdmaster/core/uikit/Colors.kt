package ru.bortsov.holdmaster.core.uikit

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalColorScheme = staticCompositionLocalOf { lightColorScheme() }

internal fun lightColorScheme(): ColorScheme {
    return ColorScheme(
        primaryBackground = Color(0xFFEDEEF0),
        secondaryBackground = Color(0xFF1C1B22),
        primaryTextColor = Color(0xFF000000),
        secondaryTextColor = Color(0xFF909090),
        thirdTextColor = Color(0xFFDADEE1),
        accentTextColor = Color(0xFF17B359),
        whiteColor = Color(0xFFFFFFFF),
        errorColor = Color(0xFFFF0000),
    )
}

internal fun darkColorScheme(): ColorScheme {
    return ColorScheme(
        primaryBackground = Color(0xFF111114),
        secondaryBackground = Color(0xFFF8F7FC),
        primaryTextColor = Color(0xFF000000),
        secondaryTextColor = Color(0xFFB1B4C2),
        thirdTextColor = Color(0xFF909090),
        accentTextColor = Color(0xFF17B359),
        whiteColor = Color(0xFFFFFFFF),
        errorColor = Color(0xFFFF0000),
    )
}

@Immutable
data class ColorScheme(
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val thirdTextColor: Color,
    val accentTextColor: Color,
    val whiteColor: Color,
    val errorColor: Color,
)