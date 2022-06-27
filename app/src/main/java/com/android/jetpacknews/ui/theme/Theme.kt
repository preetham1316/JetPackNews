package com.android.jetpacknews.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    background = Color.Black,
    primary = Purple500,
    secondary = Purple600,
    text = Color.White,
    progressBackground = Color.Black
)

private val LightColorPalette = lightColors(
    background = Color.White,
    primary = Purple500,
    secondary = Purple600,
    text = Color.Black,
    progressBackground = Color.White
)

object JetPackNewsTheme {

    val colors: JetPackNewsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

}

@Composable
fun JetPackNewsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalShapes provides shapes,
        LocalTypography provides typography
    ) {
        content()
    }
}