package com.android.jetpacknews.feature.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.jetpacknews.R
import com.android.jetpacknews.feature.splash.presentation.viewmodel.SplashViewModel
import com.android.jetpacknews.ui.theme.JetPackNewsTheme

@Composable
fun SplashScreen(hiltViewModel: SplashViewModel) {
    val state by hiltViewModel.state.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = JetPackNewsTheme.colors.purple500),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(R.drawable.app_logo), contentDescription = "")
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Jet Pack News",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (state.isLoading) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}