package com.android.jetpacknews.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class JetPackNewsColors(
    background: Color,
    primary: Color,
    secondary: Color,
    text: Color,
    progressBackground: Color,
) {

    var background by mutableStateOf(background)
        private set
    var primary by mutableStateOf(primary)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var text by mutableStateOf(text)
        private set
    var progressBackground by mutableStateOf(progressBackground)
        private set

    fun copy(
        background: Color = this.background,
        primary: Color = this.primary,
        secondary: Color = this.secondary,
        text: Color = this.text,
        progressBackground: Color = this.progressBackground
    ): JetPackNewsColors = JetPackNewsColors(
        background,
        primary,
        secondary,
        text,
        progressBackground
    )
}

val LocalColors = staticCompositionLocalOf { lightColors() }

fun lightColors(
    background: Color = Color.White,
    primary: Color = Color(0xFF6200EE),
    secondary: Color = Color(0xFF400298),
    text: Color = Color.Black,
    progressBackground: Color = Color.White
): JetPackNewsColors = JetPackNewsColors(
    background,
    primary,
    secondary,
    text,
    progressBackground
)

fun darkColors(
    background: Color = Color.Black,
    primary: Color = Color(0xFF6200EE),
    secondary: Color = Color(0xFF400298),
    text: Color = Color.White,
    progressBackground: Color = Color.Black
): JetPackNewsColors = JetPackNewsColors(
    background,
    primary,
    secondary,
    text,
    progressBackground
)

val Purple500 = Color(0xFF6200EE)
val Purple600 = Color(0xFF400298)

