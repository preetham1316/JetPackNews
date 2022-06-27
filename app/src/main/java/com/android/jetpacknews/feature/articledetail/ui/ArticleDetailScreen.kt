package com.android.jetpacknews.feature.articledetail.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import com.android.jetpacknews.util.parseDate

@Composable
fun ArticleDetailScreen(viewModel: ArticleDetailViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
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
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.body2
                    )
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.publishedAt.parseDate(),
                    color = Color.Black,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.body2
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        Text(
            text = state.title,
            color = Color.Black,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = state.description,
            color = Color.Black,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
private fun AppBar(onBackPressed: () -> Unit) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(
                    onClick = { onBackPressed() },
                    modifier = Modifier
                        .size(32.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = "Back",
                        tint = Color.Unspecified
                    )
                }
            }
        }
    )
}