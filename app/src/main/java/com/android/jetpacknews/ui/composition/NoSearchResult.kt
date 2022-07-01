package com.android.jetpacknews.ui.composition

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.android.jetpacknews.R
import com.android.jetpacknews.ui.theme.JetPackNewsTheme

@Composable
fun NoSearchResult(topPadding: Dp) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(topPadding))
        Image(
            painter = painterResource(id = R.drawable.ic_no_search_results),
            contentDescription = "No search result"
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "No articles found at present!",
            color = JetPackNewsTheme.colors.text,
            textAlign = TextAlign.Center,
            style = JetPackNewsTheme.typography.title3
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview
@Composable
fun NoSearchResultPreview() {
    NoSearchResult(topPadding = 10.dp)
}
