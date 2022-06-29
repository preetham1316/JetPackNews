package com.android.jetpacknews.feature.search.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.jetpacknews.feature.search.presentation.viemodel.SearchViewModel
import com.android.jetpacknews.ui.composition.AppBar
import com.android.jetpacknews.ui.composition.SearchBar

@Composable
fun SearchScreen(searchViewModel: SearchViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar { searchViewModel.onBackClicked() }
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val state by searchViewModel.state.collectAsState()
            Spacer(modifier = Modifier.height(10.dp))
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                searchInput = state.query,
                onSearchInputChanged = { text ->
                    searchViewModel.onSearchInputChanged(text)
                },
                onClearSearchClick = {}
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

    }
}
