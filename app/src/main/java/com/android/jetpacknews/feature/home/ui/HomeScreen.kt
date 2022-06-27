package com.android.jetpacknews.feature.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.jetpacknews.feature.home.presentation.viewmodel.HomeScreenViewModel
import com.android.jetpacknews.ui.theme.JetPackNewsTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {
    val tabData = listOf("Technology", "Movies", "Food", "Politics", "Education")
    val pagerState = rememberPagerState(
        initialPage = 0,
    )
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Column {
        AppBar()
        Column {
            ScrollableTabRow(
                modifier = Modifier.fillMaxWidth(),
                selectedTabIndex = tabIndex,
                edgePadding = 0.dp,
            ) {
                tabData.forEachIndexed { index, value ->
                    Tab(
                        modifier = Modifier.wrapContentSize(),
                        selected = tabIndex == index,
                        selectedContentColor = JetPackNewsTheme.colors.purple600,
                        unselectedContentColor = JetPackNewsTheme.colors.purple500,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(text = value, color = Color.White, maxLines = 1)
                        })
                }
            }
            HorizontalPager(
                count = tabData.size,
                state = pagerState
            ) {
                val viewModel: HomeScreenViewModel = hiltViewModel()
                // Added to get the exact clicked tab data else all the tabs will be notified
                // Ref: https://google.github.io/accompanist/pager/#reacting-to-page-changes
                LaunchedEffect(pagerState) {
                    snapshotFlow { pagerState.currentPage }.collect { page ->
                        viewModel.loadData(tabData[page])
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TabScreen(viewModel)
                }
            }
        }
    }
}

@Composable
private fun AppBar() {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        backgroundColor = JetPackNewsTheme.colors.purple500,
        elevation = 8.dp,
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Jet Pack News",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = typography.h6
                )
            }
        }
    )
}