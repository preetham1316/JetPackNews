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
    cardBackground: Color
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
    var cardBackground by mutableStateOf(cardBackground)
        private set

    fun copy(
        background: Color = this.background,
        primary: Color = this.primary,
        secondary: Color = this.secondary,
        text: Color = this.text,
        progressBackground: Color = this.progressBackground,
        cardBackground: Color = this.cardBackground
    ): JetPackNewsColors = JetPackNewsColors(
        background,
        primary,
        secondary,
        text,
        progressBackground,
        cardBackground
    )
}

val LocalColors = staticCompositionLocalOf { lightColors() }

fun lightColors(
    background: Color = Color.White,
    primary: Color = Color(0xFF6200EE),
    secondary: Color = Color(0xFF400298),
    text: Color = Color.Black,
    progressBackground: Color = Color.White,
    cardBackground: Color = Color.White
): JetPackNewsColors = JetPackNewsColors(
    background,
    primary,
    secondary,
    text,
    progressBackground,
    cardBackground
)

fun darkColors(
    background: Color = Color.Black,
    primary: Color = Color(0xFF6200EE),
    secondary: Color = Color(0xFF400298),
    text: Color = Color.White,
    progressBackground: Color = Color.Black,
    cardBackground: Color = Color.Black
): JetPackNewsColors = JetPackNewsColors(
    background,
    primary,
    secondary,
    text,
    progressBackground,
    cardBackground
)

val Purple500 = Color(0xFF6200EE)
val Purple600 = Color(0xFF400298)
val CreamWhite = Color(0xFFF0EDF3)
val GreyBlack = Color(0xFF1B1A1A)

