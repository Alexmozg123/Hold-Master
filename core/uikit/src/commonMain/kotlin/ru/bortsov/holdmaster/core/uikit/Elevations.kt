package ru.bortsov.holdmaster.core.uikit

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val LocalElevations = staticCompositionLocalOf { elevations() }

internal fun elevations(): Elevations {
    return Elevations(
        none = 0.dp,
        extraSmall = 4.dp,
        small = 8.dp,
        medium = 16.dp,
        large = 32.dp,
        extraLarge = 64.dp,
    )
}

@Immutable
data class Elevations(
    val none: Dp,
    val extraSmall: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp,
)