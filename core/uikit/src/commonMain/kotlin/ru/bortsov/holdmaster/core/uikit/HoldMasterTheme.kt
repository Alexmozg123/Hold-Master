package ru.bortsov.holdmaster.core.uikit

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun HoldMasterTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    colors: ColorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme(),
    typography: CustomTypography = typography(),
    elevations: Elevations = elevations(),
    spaces: Spaces = spaces(),
    shapes: Shapes = shapes(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorScheme provides colors,
        LocalElevations provides elevations,
        LocalShapes provides shapes,
        LocalSpaces provides spaces,
        LocalTypography provides typography,
        content = content
    )
}

object HoldMasterTheme {
    val colors: ColorScheme
        @[Composable ReadOnlyComposable] get() = LocalColorScheme.current

    val typography: CustomTypography
        @[Composable ReadOnlyComposable] get() = LocalTypography.current

    val shapes: Shapes
        @[Composable ReadOnlyComposable] get() = LocalShapes.current

    val elevations: Elevations
        @[Composable ReadOnlyComposable] get() = LocalElevations.current

    val spaces: Spaces
        @[Composable ReadOnlyComposable] get() = LocalSpaces.current
}
