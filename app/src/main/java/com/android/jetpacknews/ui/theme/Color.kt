package com.android.jetpacknews.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class JetPackNewsColors(
    purple200: Color,
    purple500: Color,
    purple700: Color,
    teal200: Color,
) {

    var purple200 by mutableStateOf(purple200)
        private set
    var purple500 by mutableStateOf(purple500)
        private set
    var purple700 by mutableStateOf(purple700)
        private set
    var teal200 by mutableStateOf(teal200)
        private set

    fun copy(
        purple200: Color = this.purple200,
        purple500: Color = this.purple500,
        purple700: Color = this.purple700,
        teal200: Color = this.teal200
    ): JetPackNewsColors = JetPackNewsColors(
        purple200,
        purple500,
        purple700,
        teal200,
    )

    fun colorsSet(
        purple200: Color = Purple200,
        purple500: Color = Purple500,
        purple700: Color = Purple700,
        teal200: Color = Teal200
    ): JetPackNewsColors = JetPackNewsColors(
        purple200,
        purple500,
        purple700,
        teal200,
    )
}

val LocalColors = staticCompositionLocalOf { colorsSet() }

fun colorsSet(
    purple200: Color = Purple200,
    purple500: Color = Purple500,
    purple700: Color = Purple700,
    teal200: Color = Teal200
): JetPackNewsColors = JetPackNewsColors(
    purple200,
    purple500,
    purple700,
    teal200,
)


val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)