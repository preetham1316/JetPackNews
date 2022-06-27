package com.android.jetpacknews.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    background = Color.Black,
    primary = Purple500,
    secondary = Purple600,
    text = Color.White,
    progressBackground = Color.Black,
    cardBackground = GreyBlack
)

private val LightColorPalette = lightColors(
    background = Color.White,
    primary = Purple500,
    secondary = Purple600,
    text = Color.Black,
    progressBackground = Color.White,
    cardBackground = CreamWhite
)

object JetPackNewsTheme {

    val colors: JetPackNewsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: JetPackNewsTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: JetPackNewsShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}

@Composable
fun JetPackNewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: JetPackNewsTypography = JetPackNewsTheme.typography,
    shapes: JetPackNewsShapes = JetPackNewsTheme.shapes,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalShapes provides shapes,
        LocalTypography provides typography
    ) {
        content()
    }
}