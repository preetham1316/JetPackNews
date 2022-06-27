package com.android.jetpacknews.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

data class JetPackNewsShapes(
    val small: RoundedCornerShape = RoundedCornerShape(4.dp),
    val medium: RoundedCornerShape = RoundedCornerShape(size = 8.dp),
    val large: RoundedCornerShape = RoundedCornerShape(0.dp),
    val iconShape: RoundedCornerShape = RoundedCornerShape(32.dp)
)

internal val LocalShapes = staticCompositionLocalOf { JetPackNewsShapes() }
