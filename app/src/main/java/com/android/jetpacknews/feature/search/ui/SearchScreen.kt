package com.android.jetpacknews.feature.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.jetpacknews.domain.model.Article
import com.android.jetpacknews.feature.home.ui.ArticleList
import com.android.jetpacknews.feature.search.presentation.model.SearchUiState
import com.android.jetpacknews.feature.search.presentation.viemodel.SearchViewModel
import com.android.jetpacknews.ui.composition.AppBar
import com.android.jetpacknews.ui.composition.FullScreenProgressBar
import com.android.jetpacknews.ui.composition.NoSearchResult
import com.android.jetpacknews.ui.composition.SearchBar
import com.android.jetpacknews.ui.theme.JetPackNewsTheme

@Composable
fun SearchScreen(searchViewModel: SearchViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar { searchViewModel.onBackClicked() }
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(JetPackNewsTheme.colors.background)
                .padding(it)
        ) {
            val state by searchViewModel.state.collectAsState()
            val query by searchViewModel.queryFlow.collectAsState()
            SearchTopSection(query,
                onSearchInputChanged = { text ->
                    searchViewModel.onSearchInputChanged(text)
                },
                onClearSearchClick = { searchViewModel.clearSearchResults() }
            )
            SearchResult(state, query.isNotEmpty()) { article ->
                searchViewModel.navigateToDetail(article)
            }
        }
    }
}

@Composable
private fun SearchTopSection(
    query: String,
    onSearchInputChanged: (String) -> Unit,
    onClearSearchClick: () -> Unit
) {
    Spacer(modifier = Modifier.height(10.dp))
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        searchInput = query,
        onSearchInputChanged = onSearchInputChanged,
        onClearSearchClick = onClearSearchClick
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun SearchResult(state: SearchUiState, hasSearchQuery: Boolean, onArticleClick: (Article) -> Unit) {
    Box {
        when {
            state.isLoading -> FullScreenProgressBar()
            state.items.isNotEmpty() -> ArticleList(state.items, onArticleClick)
            state.items.isEmpty() && hasSearchQuery && !state.isLoading -> NoSearchResult(
                topPadding = 100.dp
            )
            else -> Box(modifier = Modifier.fillMaxSize()) // To clear results and show empty view
        }
    }
}
