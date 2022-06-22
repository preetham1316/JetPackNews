package com.android.jetpacknews.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.jetpacknews.R
import com.android.jetpacknews.domain.model.Article
import com.android.jetpacknews.feature.home.presentation.viewmodel.HomeScreenViewModel

@Composable
fun TabScreen(tabQuery: String) {
    val viewModel: HomeScreenViewModel = hiltViewModel()
    viewModel.updateQuery(tabQuery)
    val state by viewModel.state.collectAsState()
    Column {
        when {
            state.isLoading -> FullScreenProgressBar()
            state.articleList.isNotEmpty() -> ArticleList(state.articleList)
        }
    }
}

@Composable
fun ArticleList(articleList: List<Article>) {
    Box {
        LazyColumn(content = {
            items(articleList) { item ->
                ArticleItem(item)
            }
        })

    }
}

@Composable
fun ArticleItem(item: Article) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {// T0D0 handle click
            },
        shape = MaterialTheme.shapes.medium,
        elevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .wrapContentHeight()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RemoteImage(item.urlToImage)
            Column(
                modifier = Modifier
                    .padding(top = 4.dp, start = 8.dp)
                    .alignByBaseline()
                    .fillMaxWidth()
            ) {
                Text(
                    text = item.title,
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.button
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.content,
                    color = Color.Black,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

@Composable
private fun RemoteImage(imageUrl: String) {
    BoxWithConstraints {
        AsyncImage(
            modifier = Modifier
                .size(104.dp)
                .clip(RoundedCornerShape(32.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .build(),
            placeholder = painterResource(R.drawable.ic_image_placeholder),
            contentDescription = "Image",
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
private fun FullScreenProgressBar() {
    Surface(color = Color.LightGray) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}