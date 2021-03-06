package com.android.jetpacknews.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.android.jetpacknews.domain.model.Article
import com.android.jetpacknews.feature.home.presentation.viewmodel.HomeScreenViewModel
import com.android.jetpacknews.ui.composition.FullScreenProgressBar
import com.android.jetpacknews.ui.composition.NoSearchResult
import com.android.jetpacknews.ui.theme.JetPackNewsTheme

@Composable
fun TabScreen(viewModel: HomeScreenViewModel) {
    val state by viewModel.state.collectAsState()
    Box {
        when {
            state.isLoading -> FullScreenProgressBar()
            state.articleList.isNotEmpty() -> ArticleList(state.articleList) { article ->
                viewModel.navigateToDetail(
                    article
                )
            }
            state.articleList.isEmpty() -> NoSearchResult(topPadding = 100.dp)
        }
    }
}

@Composable
fun ArticleList(articleList: List<Article>, onArticleClick: (Article) -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        LazyColumn(content = {
            items(articleList) { item ->
                ArticleItem(item, onArticleClick)
            }
        })
    }
}

@Composable
fun ArticleItem(item: Article, onArticleClick: (Article) -> Unit) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onArticleClick.invoke(item)
            },
        shape = JetPackNewsTheme.shapes.medium,
        elevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = JetPackNewsTheme.colors.cardBackground)
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
                    color = JetPackNewsTheme.colors.text,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    style = JetPackNewsTheme.typography.title
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.description,
                    color = JetPackNewsTheme.colors.text,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    style = JetPackNewsTheme.typography.subTitle
                )
            }
        }
    }
}
