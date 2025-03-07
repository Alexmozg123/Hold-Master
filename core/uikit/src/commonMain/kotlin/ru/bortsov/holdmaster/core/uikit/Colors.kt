package ru.bortsov.holdmaster.core.uikit

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalColorScheme = staticCompositionLocalOf { lightColorScheme() }

internal fun lightColorScheme(): ColorScheme {
    return ColorScheme(
        primaryBackground = Color(0xFFFFFFFF),
        secondaryBackground = Color(0xFFF8F7FC),
        darkAction = Color(0xFF34343C),
        darkPressedAction = Color(0xFF40404A),
        lightAction = Color(0xFFF4FAF7),
        lightPressedAction = Color(0xFFCDE8D8),
        primaryTextColor = Color(0xFF111114),
        secondaryTextColor = Color(0xFFB1B4C2),
        accentTextColor = Color(0xFF17B359),
        disabledColor = Color(0xFFE9EAEE),
    )
}

internal fun darkColorScheme(): ColorScheme {
    return ColorScheme(
        primaryBackground = Color(0xFF111114),
        secondaryBackground = Color(0xFFF8F7FC),
        darkAction = Color(0xFF34343C),
        darkPressedAction = Color(0xFF40404A),
        lightAction = Color(0xFF222225),
        lightPressedAction = Color(0xFF2B2B30),
        primaryTextColor = Color(0xFFFFFFFF),
        secondaryTextColor = Color(0xFFB1B4C2),
        accentTextColor = Color(0xFF17B359),
        disabledColor = Color(0xFF3A3B40),
    )
}

@Immutable
data class ColorScheme(
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val darkAction: Color,
    val darkPressedAction: Color,
    val lightAction: Color,
    val lightPressedAction: Color,
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val accentTextColor: Color,
    val disabledColor: Color,
)