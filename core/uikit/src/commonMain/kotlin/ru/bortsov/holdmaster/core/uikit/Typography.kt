package ru.bortsov.holdmaster.core.uikit

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val LocalTypography = staticCompositionLocalOf { typography() }

internal fun typography(): CustomTypography {
    return CustomTypography(
        h1 = DefaultTextStyle.copy(
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold
        ),
        h2 = DefaultTextStyle.copy(
            fontSize = 40.sp,
            fontWeight = FontWeight.Medium,
        ),
        h3 = DefaultTextStyle.copy(
            fontSize = 36.sp,
            fontWeight = FontWeight.Medium,
        ),
        h4 = DefaultTextStyle.copy(
            fontSize = 32.sp,
            fontWeight = FontWeight.Medium,
        ),
        h5 = DefaultTextStyle.copy(
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
        ),
        h6 = DefaultTextStyle.copy(
            fontSize = 24.sp,
        ),
        subtitle1 = DefaultTextStyle.copy(
            fontSize = 20.sp,
        ),
        subtitle2 = DefaultTextStyle.copy(
            fontSize = 18.sp,
        ),
        body1 = DefaultTextStyle.copy(
            fontSize = 16.sp,
        ),
        body2 = DefaultTextStyle.copy(
            fontSize = 14.sp,
        ),
        caption = DefaultTextStyle.copy(
            fontSize = 12.sp,
        )
    )
}

@Immutable
data class CustomTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val h6: TextStyle,
    val subtitle1: TextStyle,
    val subtitle2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val caption: TextStyle,
)

private val DefaultTextStyle = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
)