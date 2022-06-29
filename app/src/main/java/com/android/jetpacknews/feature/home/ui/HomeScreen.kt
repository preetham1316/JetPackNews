package com.android.jetpacknews.feature.home.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.jetpacknews.R
import com.android.jetpacknews.feature.home.presentation.viewmodel.HomeScreenViewModel
import com.android.jetpacknews.ui.theme.JetPackNewsTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel) {
    val tabData = listOf("Technology", "Movies", "Food", "Politics", "Education")
    val pagerState = rememberPagerState(
        initialPage = 0,
    )
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        AppBar { homeScreenViewModel.onSearchClicked() }
    }) {
        Column(modifier = Modifier.padding(it)) {
            ScrollableTabRow(
                modifier = Modifier.fillMaxWidth(),
                selectedTabIndex = tabIndex,
                edgePadding = 0.dp,
            ) {
                tabData.forEachIndexed { index, value ->
                    Tab(
                        modifier = Modifier.wrapContentSize(),
                        selected = tabIndex == index,
                        selectedContentColor = JetPackNewsTheme.colors.primary,
                        unselectedContentColor = JetPackNewsTheme.colors.secondary,
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
                // Added to get the exact clicked tab data else all the tabs will be notified
                // Ref: https://google.github.io/accompanist/pager/#reacting-to-page-changes
                LaunchedEffect(pagerState) {
                    snapshotFlow { pagerState.currentPage }.collect { page ->
                        homeScreenViewModel.loadData(tabData[page])
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(JetPackNewsTheme.colors.background),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TabScreen(homeScreenViewModel)
                }
            }
        }
    }
}

@Composable
private fun AppBar(onSearchClicked: () -> Unit) {
    val context = LocalContext.current
    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(JetPackNewsTheme.colors.primary),
        colors = TopBarColors(),
        title = {
            Text(
                text = stringResource(R.string.app_title),
                color = Color.White,
                textAlign = TextAlign.Center,
                style = JetPackNewsTheme.typography.title3
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(context, "Coming Soon!", Toast.LENGTH_SHORT).show()
            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Localized description"
                )
            }
        }, actions = {
            IconButton(onClick = onSearchClicked) {
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Localized description"
                )
            }
        }
    )
}

@SuppressLint("UnrememberedMutableState")
class TopBarColors : TopAppBarColors {

    @Composable
    override fun actionIconContentColor(scrollFraction: Float) =
        mutableStateOf(Color.White)

    @Composable
    override fun containerColor(scrollFraction: Float) =
        mutableStateOf(JetPackNewsTheme.colors.primary)

    @Composable
    override fun navigationIconContentColor(scrollFraction: Float) =
        mutableStateOf(Color.White)

    @Composable
    override fun titleContentColor(scrollFraction: Float) = mutableStateOf(Color.White)
}
