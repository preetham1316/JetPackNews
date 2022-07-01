package com.android.jetpacknews.feature.articledetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.jetpacknews.R
import com.android.jetpacknews.feature.articledetail.presentation.viewmodel.ArticleDetailViewModel
import com.android.jetpacknews.ui.composition.AppBar
import com.android.jetpacknews.ui.theme.JetPackNewsTheme
import com.android.jetpacknews.util.parseDate

@Composable
fun ArticleDetailScreen(viewModel: ArticleDetailViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(JetPackNewsTheme.colors.background)
    ) {
        AppBar {
            viewModel.onBackClicked()
        }
        DetailScreenBody(viewModel)
    }
}

@Composable
fun DetailScreenBody(viewModel: ArticleDetailViewModel) {
    val state by viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(state.urlToImage)
                .build(),
            placeholder = painterResource(R.drawable.ic_image_placeholder),
            contentDescription = "Image",
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(10.dp))
        if (state.author.isNotEmpty() || state.publishedAt.isNotEmpty()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                if (state.author.isNotEmpty()) {
                    Text(
                        text = "Author: ${state.author}",
                        color = JetPackNewsTheme.colors.text,
                        textAlign = TextAlign.Start,
                        style = JetPackNewsTheme.typography.title2
                    )
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.publishedAt.parseDate(),
                    color = JetPackNewsTheme.colors.text,
                    textAlign = TextAlign.End,
                    style = JetPackNewsTheme.typography.title2
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        Text(
            text = state.title,
            color = JetPackNewsTheme.colors.text,
            textAlign = TextAlign.Start,
            style = JetPackNewsTheme.typography.title3
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = state.description,
            color = JetPackNewsTheme.colors.text,
            textAlign = TextAlign.Start,
            style = JetPackNewsTheme.typography.title2
        )
    }
}
