package com.android.jetpacknews.feature.home.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.jetpacknews.R
import com.android.jetpacknews.ui.theme.JetPackNewsTheme

@Composable
fun RemoteImage(imageUrl: String, size: Dp = 104.dp) {
    BoxWithConstraints {
        AsyncImage(
            modifier = Modifier
                .size(size)
                .clip(JetPackNewsTheme.shapes.iconShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .build(),
            placeholder = painterResource(R.drawable.ic_image_placeholder),
            contentDescription = "Image",
            contentScale = ContentScale.FillBounds
        )
    }
}
